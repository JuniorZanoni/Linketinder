package model.competencia

import service.competencia.Competencia

interface IDAOCompetencia {

    List<Competencia> getCompetencias(Integer id)
    List getCompetenciasNoHave(Integer id)
    void add(Integer id, Integer idCompetencia)
    void remove(Integer id, Integer idCompetencia)

}