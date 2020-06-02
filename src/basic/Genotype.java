package basic;

public class Genotype {
	// 0, 1, 2 are acceptable values for each gene
	// 0 represents a gene that is completely recessive
	// 1 represents a gene with one dominant and one recessive allele
	// 2 represents a gene with both dominant alleles
	private int[] genes;

	// for convention, gene markers should all be lowercase
	private char[] geneMarkers;

	// we know that in animal crossing that there are a total of four genes...
	// but we will let this class determine such dynamically
	public Genotype(String geneSequence) throws IllegalArgumentException {

		validateGeneSequence(geneSequence);

		int numberOfGenes = geneSequence.length() / 2;

		initializeArrays(numberOfGenes);

		populateGenes(geneSequence, numberOfGenes);

	}

	public Genotype(int[] geneSequence) {

		validateGeneSequence(geneSequence);

		initializeArrays(geneSequence.length);

		populateGenes(geneSequence);
	}

	private void validateGeneSequence(String geneSequence) throws IllegalArgumentException {

		if (geneSequence.length() % 2 != 0) {
			throw new IllegalArgumentException("Uneven alleles in gene: " + geneSequence);
		}

		if (geneSequence.length() == 0 || geneSequence.isEmpty()) {
			throw new IllegalArgumentException("Empty gene sequence ");
		}
	}

	private void validateGeneSequence(int[] geneSequence) throws IllegalArgumentException {

		if (geneSequence == null || geneSequence.length == 0) {
			throw new IllegalArgumentException("The array as the  geneSequence is empty");
		}
	}

	private void populateGenes(int[] geneSequence) throws IllegalArgumentException {
		// each gene needs to be assigned a representative character, or marker
		char marker = 'a';

		for (int i = 0; i < geneSequence.length; i++) {

			findGeneExpression(geneSequence, marker, i);

			// change the gene marker for the next gene
			marker++;
			if (marker > 'z') {
				marker = 'a';
			}
		}
	}

	private void populateGenes(String geneSequence, int numberOfGenes) throws IllegalArgumentException {

		for (int index = 0; index < numberOfGenes; index++) {

			// first get the chars from the string
			char first = geneSequence.charAt(0);
			char second = geneSequence.charAt(1);
			geneSequence = geneSequence.substring(2);

			validateGene(first, second);

			int geneExpression = findGeneExpression(first, second);

			// store the geneExpression
			genes[index] = geneExpression;

			// store the character used to mark the gene
			// it will be lowercase by convention
			geneMarkers[index] = Character.toLowerCase(first);
		}
	}

	private void findGeneExpression(int[] geneSequence, char marker, int index) throws IllegalArgumentException {

		if (index < 0 || index >= geneSequence.length) {
			throw new IllegalArgumentException("Index: " + index + " is not a valid index for the geneSequence");
		}

		if (geneSequence[index] >= 0 && geneSequence[index] <= 2) {

			genes[index] = geneSequence[index];
			geneMarkers[index] = marker;

		} else {
			throw new IllegalArgumentException("The gene at index " + index
					+ " does not have a valid value between 0 and 2. It's value: " + geneSequence[index]);
		}
	}

	private int findGeneExpression(char first, char second) {
		// we need to assign the gene integer based on the capitalization of each
		// character

		int geneExpression = 0;
		if (Character.isUpperCase(first)) {
			geneExpression++;
		}
		if (Character.isUpperCase(second)) {
			geneExpression++;
		}
		return geneExpression;
	}

	private int initializeArrays(int numberOfGenes) {
		genes = new int[numberOfGenes];
		geneMarkers = new char[numberOfGenes];
		return numberOfGenes;
	}

	private void validateGene(char first, char second) throws IllegalArgumentException {

		if (Character.toLowerCase(first) != Character.toLowerCase(second)) {
			throw new IllegalArgumentException("Two alleles of the same gene do not match");
		}
		if (!Character.isLetter(first) || !Character.isLetter(second)) {
			throw new IllegalArgumentException("The gene: " + first + "" + second + " is not in the alphabet");
		}
	}

	/**
	 * @return The character used to represent the gene at the given index
	 */
	public char getAlleleRepresentation(int index) {
		return geneMarkers[index];
	}

	/**
	 * @return The number used to represent the gene's value at the given index
	 */
	public int getGeneAtIndex(int index) {
		return genes[index];
	}

	// should I combine the bottom two methods?
	/**
	 * @return The String of the gene's value located at the given index
	 */
	public String getGeneRepresentation(int index) {
		String representation = "";
		if (index >= 0 && index < genes.length) {
			char marker = geneMarkers[index];
			int gene = genes[index];
			representation = findGeneRepresentation(marker, gene);
		}
		return representation;
	}

	/**
	 * @return The entire genome represented as a String.
	 */
	public String getGeneRepresentation() {
		String representation = "";
		for (int i = 0; i < genes.length; i++) {
			representation += this.getGeneRepresentation(i);
		}
		return representation;
	}

	private String findGeneRepresentation(char marker, int gene) {
		String representation = "";
		switch (gene) {
		case 0:
			representation = "" + marker + "" + marker;
			break;
		case 1:
			representation = "" + Character.toUpperCase(marker) + "" + marker;
			break;
		case 2:
			representation = "" + Character.toUpperCase(marker) + "" + Character.toUpperCase(marker);
			break;
		}
		return representation;
	}

	public int getNumberOfGenes() {
		return genes.length;
	}

	@Override
	public boolean equals(Object compareTo) {
		boolean equal = true;
		if (compareTo instanceof Genotype) {
			// find lengths of each and compare them
			if (this.getNumberOfGenes() == ((Genotype) compareTo).getNumberOfGenes()) {
				for (int index = 0; index < this.getNumberOfGenes(); index++) {
					if(this.getGeneAtIndex(index) != ((Genotype) compareTo).getGeneAtIndex(index)) {
						equal = false;
						break;
					}
				}
			} else {
				equal = false;
			}
		} else {
			equal = false;
		}
		return equal;
	}
}
