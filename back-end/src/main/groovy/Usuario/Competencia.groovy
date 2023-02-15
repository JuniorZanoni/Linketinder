package Usuario

import Model.ModelCompetencia


class Competencia {
    List competencias = new ModelCompetencia().getCompetencias()

    void printCompetencias() {
        this.competencias.forEach { competencia ->
            println "${competencia[0]} - ${competencia[1]}"
        }
    }

    void addCompetencia(Candidato candidato, Integer index) {
        if(competencias.find(e -> e[0] == index.toString())) {
            new ModelCompetencia().addCompetenciaUsuario(candidato, index)
        } else {
            throw new Exception()
        }
    }

    void removeCompetencia(Candidato candidato, Integer index) {
        new ModelCompetencia().deleteCompetenciaUsuario(index, candidato.email)
    }

    void removeCompetenciaList(Integer index) {
        this.competencias.remove(competencias.find(competencia -> competencia[0] == index.toString()))
    }

    List getCompetenciasUsuario(Candidato candidato) {
        return new ModelCompetencia().getCompetenciasUsuario(candidato.email)
    }
}
