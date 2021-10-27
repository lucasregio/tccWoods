package com.woods.tcc.business;

import com.woods.tcc.model.Provider;

public class EvaluationBusiness {

  public void evaluateProvider(Long value, Provider provider){
    provider.setEvaluation(value);
  }

}
