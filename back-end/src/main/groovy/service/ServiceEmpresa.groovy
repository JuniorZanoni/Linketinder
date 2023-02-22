package service


import model.DAOEmpresa
import model.DAOMatch
import model.DBConnection
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

    static Integer getId(Empresa empresa) {
        return new DAOEmpresa().getId(empresa)
    }

    static void delete(Empresa empresa) {
        new DAOEmpresa().delete(empresa)
    }

    static void update(Empresa empresa, Integer idEmpresa) {
        new DAOEmpresa().update(empresa, idEmpresa)
    }

    static List<Map> getMatchesEmpresa(Integer vagaId) {
        return new DAOMatch().getMatchesEmpresa(vagaId)
    }
}
