package controller

import service.ServiceEmpresa
import service.user.Empresa

class ControllerEmpresa {
    static boolean save(Map<String, String> empresaBO) {
        return new ServiceEmpresa().save(empresaBO)
    }

    static void delete(Empresa empresa) {
        new ServiceEmpresa().delete(empresa)
    }

    static void update(Empresa empresa, Integer idEmpresa) {
        new ServiceEmpresa().update(empresa, idEmpresa)
    }

    static Integer getId(Empresa empresa) {
        return ServiceEmpresa.getId(empresa)
    }
}
