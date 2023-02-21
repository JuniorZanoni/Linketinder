package service.competencia

class Competencia {
    String name

    Competencia(String competencia) {
        this.name = competencia
    }

    static List<Map> convertListInBO(List competencias) {
        List<Map> competenciasBO = []
        competencias.forEach(competencia -> {
            competenciasBO.add([id: competencia[0], name: competencia[1]])
        })
        return competenciasBO
    }

    static boolean checkExistsInList(List<Map> competenciasNotUser, String indexCompetencia) {
        return competenciasNotUser.find(e -> e.id == indexCompetencia)
    }
}
