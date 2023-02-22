package controller

import service.ServiceLogin
import service.user.User

class ControllerLogin {
    static User login(String email, String senha) {
        return ServiceLogin.login(email, senha)
    }
}
