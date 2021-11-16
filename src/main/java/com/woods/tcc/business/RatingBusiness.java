package com.woods.tcc.business;

import com.woods.tcc.model.Provider;

public class RatingBusiness {

  public void rateProvider(Long value, Provider provider){
    provider.setRating(value);
  }

}
