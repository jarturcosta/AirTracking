/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package https.airtracking.gitlab.io.airtracking;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author jarturcosta
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "https.airtracking.gitlab.io.airtracking")
public class WebConfig {
    
}

