package org.ck.converter.cron.cnvrsns.impl;

import org.ck.converter.cron.ConversionFactory;

public class MinutesConverter {

	// More than one minute expression with comma separator
	public String convertMinutesWithComma(String[] inputsExpAsArray, String inputTimeZone) throws Exception {
		
		StringBuffer cnvrtdMinsWthComma = new StringBuffer();

        String[] tmp = inputsExpAsArray;

        String[] mins = inputsExpAsArray[1].split(",");
        
        for (int i = 0; i < mins.length; i++) {
            tmp[1] = mins[i];
            String tmpExp = buildExpressionFromArray(tmp);
            ConversionFactory factory = ConversionFactory.getConversionFactory(tmpExp, inputTimeZone);
            if (i == 0) {
            	cnvrtdMinsWthComma.append(factory.getMins());
            } else {
            	cnvrtdMinsWthComma.append(",").append(factory.getMins() );
            }
        }
        
		return cnvrtdMinsWthComma.toString();    
	}
	
	
    private static String buildExpressionFromArray(String[] ar) {
        String exp = "";

        for (int i = 0; i < ar.length; i++) {
            if (i != ar.length - 1) {
                exp = exp + ar[i] + " ";
            } else {
                exp = exp + ar[i];
            }
        }

        return exp;
    }
    
}
