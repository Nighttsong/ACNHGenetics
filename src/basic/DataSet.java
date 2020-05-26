package basic;

public class DataSet {
	/*
	 * A data set: HAS a genomeList (a list of every possible genotype) HAS
	 * expressions that correspond with every genotype HAS a list of valid
	 * Phenotypes; each expression MUST be one of the valid Phenotypes "Empty" or
	 * "Undetermined" is also a valid Phenotype
	 */
	private Genotype[] genome;
	private int[] expressions;
	private String[] validPhenotypes;

	/*
	 * Constructing a dataset: There should be two ways to create a new dataset 1-
	 * input a data file/file name to get this data 2- input a list of valid
	 * phenotypes, and a list of individual genes (ie. A, B, C)
	 */
	public DataSet(String filename) {// or do we want to pass the actual file?

	}

	public DataSet(String[] validPhenotypes, char[] genes) {

	}

	/*
	 * What can you DO with a data set?: Change data Apply only valid Phenotypes to
	 * expressions using either an index or string search through the genome for its
	 * index number or expression Compare data set with another data set Print off
	 * (to console or notepad) the data in a dataset Clear the exprerssion's list
	 */

	
	private void generateGenome (char [] genes) {
		
	}
	// GET
	public String getGenotype(int index) {
		return "";
	}

	public String getExpression(int index) {
		return "";
	}

	public String getExpression(String gene) {
		return "";
	}

	public int getExpressionIndex(String phenotype) {
		return -1;
	}

	public Genotype[] getGenesWithPhenotype(String phenotype) {
		return null;
	}

	// SET
	public void setPhenotype(int index, String phenotype) throws IllegalArgumentException {

	}

	public void setPhenotype(Genotype gene, String phenotype) throws IllegalArgumentException {

	}

	public void setPhenotype(String gene, String phenotype) throws IllegalArgumentException {

	}
	//
	public void overridePhenotype(int index, String phenotype) throws IllegalArgumentException {

	}

	public void overridePhenotype(Genotype gene, String phenotype) throws IllegalArgumentException {

	}

	public void overridePhenotype(String gene, String phenotype) throws IllegalArgumentException {

	}
	
	// PRINT
	
	public void printDataSet() {
		
	}
	public void printDataSet(String filename) {
		
	}
	public void compareDataSet(DataSet secondDataSet) {
		
	}
	public void compareDataSet(DataSet secondDataSet, String filename) {
		
	}
	/*
	 * should we be able to check for incomplete data? Read: a genome that doesn't
	 * have all potential possibilities? This would only be able to happen in a data
	 * set that is constructed via an input file
	 */
}
