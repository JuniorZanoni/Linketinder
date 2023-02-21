package model.modelCompetencia

import service.competencia.Competencia

interface IModelCompetencia {

    List<Competencia> getCompetencias(Integer id)
    List getCompetenciasNoHave(Integer id)
    void saveCompetecia(Integer id, Integer idCompetencia)
    void deleteCompetecia(Integer id, Integer idCompetencia)

}