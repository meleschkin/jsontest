package org.meleschkin.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
import org.meleschkin.eo.Login;
import org.meleschkin.eo.LoginTyp;

@Log4j2
public class EnumTest {

    public static void main(String[] args) {
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.INFO);
        try {
            Login userLogin = getLogin();
            log.info(userLogin.toString());
            String json = jsonLogin(userLogin);
            log.info(json);
            Login l = getLogin(json);
            if (l != null)
                log.info(l.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static Login getLogin() {
        Login userLogin = new Login();
        userLogin.setUser("user1");
        userLogin.setPassword("abc123");
        userLogin.setTyp(LoginTyp.UNPW);
        return userLogin;
    }

    @SneakyThrows
    public static String jsonLogin(Login userLogin) {
        if (userLogin == null) {
            return "";
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userLogin);
    }

    @SneakyThrows
    public static Login getLogin(String json) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        return mapper.readValue(json, Login.class);
    }

}
