package pe.angeles.mateapp.controller;

import pe.angeles.mateapp.service.MateService;

public class MateController {

  private MateService mateService;

  public MateController() {
    mateService = new MateService();
  }
  
  public long factorial(int n) {
    return mateService.factorial(n);
  }
  
  
  
}
