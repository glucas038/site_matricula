package br.com.fiesc.admin_educacao.utils;

public class ManipulaString {
	
	public static String letraMaiuscula(String input) {
        StringBuilder result = new StringBuilder(input.length());
        boolean capitalizeNext = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                c = Character.toTitleCase(c);
                capitalizeNext = false;
            }
            result.append(c);
        }

        return result.toString();
    }

}
