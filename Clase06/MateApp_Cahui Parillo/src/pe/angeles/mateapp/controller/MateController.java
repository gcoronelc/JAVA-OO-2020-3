
package pe.angeles.mateapp.controller;

import pe.angeles.mateapp.service.MateService;

public class MateController {
    private MateService mateService;

    public MateController() {
        mateService=new MateService();
        
    }
   

    public long factorial(int n) {
        return mateService.factorial(n);
    }
    public long mcm(int n1, int n2) {
        return mateService.mcm(n1, n2);
    }
    public long mcd(int n1, int n2) {
        return mateService.mcd(n1, n2);
    }
    public String esprimo(int n) {
        return mateService.esPrimo(n);
    }
    public String fibonacc(int n){
        return mateService.fibonacc(n);
    }
}
