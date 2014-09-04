
public class FourPixCreator {

    // for representing the solution
    private String[] solutionLetters;

    // for representing the URL1
    private String URL1;

    // for representing the URL2
    private String URL2;

    // for representing the URL3
    private String URL3;

    // for representing the URL4
    private String URL4;

    // for representing the word 1
    private String word1;

    // for representing the word 2
    private String word2;

    // for representing the word 3
    private String word3;

    // for representing the word 4
    private String word4;
    
    /**
     * Set method for the variable solution letters
     */
    public void setSolutionLetters(String[] a_solution_letters) {
	solutionLetters = a_solution_letters;
    }

    /**
     * Set method for the variable URL1
     */
    public void setURL1(String a__u_r_l1) {
	URL1 = a__u_r_l1;
    }

    /**
     * Set method for the variable URL2
     */
    public void setURL2(String a__u_r_l2) {
	URL2 = a__u_r_l2;
    }

    /**
     * Set method for the variable URL3
     */
    public void setURL3(String a__u_r_l3) {
	URL3 = a__u_r_l3;
    }

    /**
     * Set method for the variable URL4
     */
    public void setURL4(String a__u_r_l4) {
	URL4 = a__u_r_l4;
    }

    /**
     * Set method for the variable word1
     */
    public void setWord1(String a_word1) {
	word1 = a_word1;
    }

    /**
     * Set method for the variable word2
     */
    public void setWord2(String a_word2) {
	word2 = a_word2;
    }

    /**
     * Set method for the variable word3
     */
    public void setWord3(String a_word3) {
	word3 = a_word3;
    }

    /**
     * Set method for the variable word4
     */
    public void setWord4(String a_word4) {
	word4 = a_word4;
    }

    /**
     * Get method for the variable solution letters
     */
    public String[] getSolutionLetters() {
	return solutionLetters;
    }

    /**
     * Get method for the variable URL1
     */
    public String getURL1() {
	return URL1;
    }

    /**
     * Get method for the variable URL2
     */
    public String getURL2() {
	return URL2;
    }

    /**
     * Get method for the variable URL3
     */
    public String getURL3() {
	return URL3;
    }

    /**
     * Get method for the variable URL4
     */
    public String getURL4() {
	return URL4;
    }

    /**
     * Get method for the variable word1
     */
    public String getWord1() {
	return word1;
    }

    /**
     * Get method for the variable word2
     */
    public String getWord2() {
	return word2;
    }

    /**
     * Get method for the variable word3
     */
    public String getWord3() {
	return word3;
    }

    /**
     * Get method for the variable word4
     */
    public String getWord4() {
	return word4;
    }
    
    /**
     * Gets the actual length of the solution word.
     * (Necessary for multibyte characters)
     * 
     * @return the actual length of the solution word
     */
    public int getSolutionLength() {
	return solutionLetters.length;
    }
    
    /**
     * Gets the concatenated solution letters.
     * 
     * @return the solution word
     */
    public String getSolutionWord() {
	StringBuilder sb = new StringBuilder();
	for (String solutionLetter : solutionLetters) {
	    sb.append(solutionLetter);
	}
	return sb.toString();
    }

    /**
     * Returns the String representation of FourPixCreator object
     */
    public String toString() {
	String temp = "\nsolutionWord = " + getSolutionWord() + "\nURL1 = " + URL1
		+ "\nURL2 = " + URL2 + "\nURL3 = " + URL3 + "\nURL4 = " + URL4
		+ "\nword1 = " + word1 + "\nword2 = " + word2 + "\nword3 = "
		+ word3 + "\nword4 = " + word4;
	return temp;
    }

}