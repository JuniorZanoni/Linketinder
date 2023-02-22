package service

import model.empresa.DAOEmpresa
import service.user.Empresa

class ServiceEmpresa {
    static boolean save(Map<String, String> empresaBO) {
        Empresa empresa = new Empresa(
                empresaBO.name,
                empresaBO.email,
                empresaBO.cnpj,
                empresaBO.description,
                empresaBO.country,
                empresaBO.cep,
                empresaBO.password
        )

        return new DAOEmpresa().save(empresa)
    }
}
