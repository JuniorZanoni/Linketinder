package controller

import service.ServiceEmpresa

class ConstrollerEmpresa {
    static boolean save(Map<String, String> empresaBO) {
        return new ServiceEmpresa().save(empresaBO)
    }
}
