package work.gg3083.template.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import work.gg3083.template.auth.handler.AccessDeniedAuthHandler;
import work.gg3083.template.auth.handler.FailLoginHandler;
import work.gg3083.template.auth.handler.SuccessLoginHandler;
import work.gg3083.template.auth.handler.SuccessLogoutHandler;
import work.gg3083.template.commom.AuthConst;
import work.gg3083.template.filter.TokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private FailLoginHandler failLoginHandler;
    @Autowired
    private SuccessLoginHandler successLoginHandler;
    @Autowired
    private SuccessLogoutHandler successLogoutHandler;
    @Autowired
    private  CustomUserDetailsService customUserDetailsService;
    @Autowired
    private AccessDeniedAuthHandler accessDeniedAuthenticationHandler;



    /**
     * 注入身份管理器bean
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    /**
     * 注入自定义权限管理
     *
     * @return
     * @throws Exception
     */
    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler2() {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(new CustomPermissionEvaluator());
        return handler;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(
                new PasswordEncoder() {
                    @Override
                    public String encode(CharSequence charSequence) {
                        return charSequence.toString();
                    }

                    @Override
                    public boolean matches(CharSequence charSequence, String s) {
                        return s.equals(charSequence.toString());
                    }
                })
        .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .failureHandler(failLoginHandler) // 自定义登录失败处理
                .successHandler(successLoginHandler) // 自定义登录成功处理
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessHandler(successLogoutHandler)
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/user/login") // 自定义登录路径
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .authorizeRequests()// 对请求授权
                .antMatchers(AuthConst.NO_AUTH_RESOURCES).permitAll()// 这些页面不需要身份认证
                .antMatchers("/api/**")
                .access("@authPreCheckEvaluatorImpl2.check(authentication)")
                .anyRequest()//其他请求需要认证
                .authenticated().and().exceptionHandling()
                .accessDeniedHandler(accessDeniedAuthenticationHandler)
                .and()
                .addFilter(new TokenFilter(authenticationManager()))
                .csrf().disable();// 禁用跨站攻击
    }

    @Override
    public void configure(WebSecurity web) {
        //对于在header里面增加token等类似情况，放行所有OPTIONS请求。
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

}
