import controller.ControllerLogin
import service.user.User
import view.login.LoginView

User user = ControllerLogin.login("zanoni@gmail.com", "Juninho95!")

println user.getClass().getSimpleName()


