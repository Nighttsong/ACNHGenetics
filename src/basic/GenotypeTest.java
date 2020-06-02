package basic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Genotype Class Tests")
class GenotypeTest {

	private static final Logger log = Logger.getLogger(Genotype.class.getSimpleName());
	Genotype genotypeTester;

	@AfterEach
	void initialize() {
		genotypeTester = null;
		log.info("The genotype tester has been set back to null");
	}
	// what do we want the genotype to do?
	// we want to be able to store gene information:
	// which letters represent the genes?
	// which genes are recessive, mixed, and fully dominant

	// how to create the gene
	// check that it ensures that the data is valid
	// what should happen if the letter pairs don't correspond?
	// i.e. ARrr?

	// make sure that we can get the information back out of the genotype
	// we should be able to initialize to any size
	// what happens if we try to initialize a size of more than 26, with an array of
	// int alleles?

	@Nested
	@DisplayName("Constructor Tests")
	class ConstructorTests {

		@Nested
		@DisplayName("Constructor: String Input")
		class ConstructorWithStringInput {

			@Test
			@DisplayName("Creation: Single Gene")
			void testGenotypeSingleGene() {
				assertAll(() -> {
					genotypeTester = new Genotype("Aa");
					assertNotNull(genotypeTester);
					char expectedGeneMarker = 'a';
					int expectedGeneValue = 1;
					assertEquals(expectedGeneMarker, genotypeTester.getAlleleRepresentation(0));
					assertEquals(expectedGeneValue, genotypeTester.getGeneAtIndex(0));
				}, () -> {
					genotypeTester = new Genotype("AA");
					assertNotNull(genotypeTester);
					char expectedGeneMarker = 'a';
					int expectedGeneValue = 2;
					assertEquals(expectedGeneMarker, genotypeTester.getAlleleRepresentation(0));
					assertEquals(expectedGeneValue, genotypeTester.getGeneAtIndex(0));

				}, () -> {
					genotypeTester = new Genotype("aa");
					assertNotNull(genotypeTester);
					char expectedGeneMarker = 'a';
					int expectedGeneValue = 0;
					assertEquals(expectedGeneMarker, genotypeTester.getAlleleRepresentation(0));
					assertEquals(expectedGeneValue, genotypeTester.getGeneAtIndex(0));
				}, () -> {
					genotypeTester = new Genotype("CC");
					assertNotNull(genotypeTester);
					char expectedGeneMarker = 'c';
					int expectedGeneValue = 2;
					assertEquals(expectedGeneMarker, genotypeTester.getAlleleRepresentation(0));
					assertEquals(expectedGeneValue, genotypeTester.getGeneAtIndex(0));

				});

				// fail("Test not implemented!");
			}

			@Test
			@DisplayName("Creation: Multiple Genes")
			void testGenotypeMultipeGene() {
				assertAll(() -> {
					// testing with two genes
					genotypeTester = new Genotype("CCdd");
					assertNotNull(genotypeTester);
					char expectedGeneMarkerAt0 = 'c';
					int expectedGeneValueAt0 = 2;
					char expectedGeneMarkerAt1 = 'd';
					int expectedGeneValueAt1 = 0;
					assertEquals(expectedGeneMarkerAt0, genotypeTester.getAlleleRepresentation(0));
					assertEquals(expectedGeneValueAt0, genotypeTester.getGeneAtIndex(0));
					assertEquals(expectedGeneMarkerAt1, genotypeTester.getAlleleRepresentation(1));
					assertEquals(expectedGeneValueAt1, genotypeTester.getGeneAtIndex(1));
				}, () -> {
					// testing with four genes
					genotypeTester = new Genotype("aArrKkLL");
					assertNotNull(genotypeTester);
					char expectedGeneMarkerAt0 = 'a';
					int expectedGeneValueAt0 = 1;
					char expectedGeneMarkerAt1 = 'r';
					int expectedGeneValueAt1 = 0;
					char expectedGeneMarkerAt2 = 'k';
					int expectedGeneValueAt2 = 1;
					char expectedGeneMarkerAt3 = 'l';
					int expectedGeneValueAt3 = 2;
					assertEquals(expectedGeneMarkerAt0, genotypeTester.getAlleleRepresentation(0));
					assertEquals(expectedGeneValueAt0, genotypeTester.getGeneAtIndex(0));
					assertEquals(expectedGeneMarkerAt1, genotypeTester.getAlleleRepresentation(1));
					assertEquals(expectedGeneValueAt1, genotypeTester.getGeneAtIndex(1));
					assertEquals(expectedGeneMarkerAt2, genotypeTester.getAlleleRepresentation(2));
					assertEquals(expectedGeneValueAt2, genotypeTester.getGeneAtIndex(2));
					assertEquals(expectedGeneMarkerAt3, genotypeTester.getAlleleRepresentation(3));
					assertEquals(expectedGeneValueAt3, genotypeTester.getGeneAtIndex(3));
				}, () -> {
					// testing with five genes
					genotypeTester = new Genotype("aaBbCcAAjJ");
					assertNotNull(genotypeTester);
					char expectedGeneMarkerAt0 = 'a';
					int expectedGeneValueAt0 = 0;
					char expectedGeneMarkerAt1 = 'b';
					int expectedGeneValueAt1 = 1;
					char expectedGeneMarkerAt2 = 'c';
					int expectedGeneValueAt2 = 1;
					char expectedGeneMarkerAt3 = 'a';
					int expectedGeneValueAt3 = 2;
					char expectedGeneMarkerAt4 = 'j';
					int expectedGeneValueAt4 = 1;
					assertEquals(expectedGeneMarkerAt0, genotypeTester.getAlleleRepresentation(0));
					assertEquals(expectedGeneValueAt0, genotypeTester.getGeneAtIndex(0));
					assertEquals(expectedGeneMarkerAt1, genotypeTester.getAlleleRepresentation(1));
					assertEquals(expectedGeneValueAt1, genotypeTester.getGeneAtIndex(1));
					assertEquals(expectedGeneMarkerAt2, genotypeTester.getAlleleRepresentation(2));
					assertEquals(expectedGeneValueAt2, genotypeTester.getGeneAtIndex(2));
					assertEquals(expectedGeneMarkerAt3, genotypeTester.getAlleleRepresentation(3));
					assertEquals(expectedGeneValueAt3, genotypeTester.getGeneAtIndex(3));
					assertEquals(expectedGeneMarkerAt4, genotypeTester.getAlleleRepresentation(4));
					assertEquals(expectedGeneValueAt4, genotypeTester.getGeneAtIndex(4));
				}

				);

				// fail("Test not implemented!");
			}

			@Nested
			@DisplayName("Faulty Genotype Tests")
			class StringInputConstructorFlaws {
				@Test
				@DisplayName("EmptySequence")
				void testEmptySequence() {
					assertThrows(IllegalArgumentException.class, () -> genotypeTester = new Genotype(""));
				}

				@Test
				@DisplayName("Uneven alleles")
				void testUnevenAlleles() {
					assertAll(() -> {
						assertThrows(IllegalArgumentException.class, () -> genotypeTester = new Genotype("Aab"));
					}, () -> {
						assertThrows(IllegalArgumentException.class, () -> genotypeTester = new Genotype("Aab"));
					}, () -> {
						assertThrows(IllegalArgumentException.class, () -> genotypeTester = new Genotype("AabCcdd"));
					});

				}

				@Test
				@DisplayName("Non Alphabetical chars")
				void testNonAlpabeticalChars() {
					assertAll(() -> {
						assertThrows(IllegalArgumentException.class, () -> genotypeTester = new Genotype("''"));
					}, () -> {
						assertThrows(IllegalArgumentException.class, () -> genotypeTester = new Genotype("??"));
					}, () -> {
						assertThrows(IllegalArgumentException.class, () -> genotypeTester = new Genotype("pp.."));
					}, () -> {
						assertThrows(IllegalArgumentException.class, () -> genotypeTester = new Genotype("A'"));
					}, () -> {
						assertThrows(IllegalArgumentException.class, () -> genotypeTester = new Genotype("?a"));
					}, () -> {
						assertThrows(IllegalArgumentException.class, () -> genotypeTester = new Genotype("-Aa="));
					}, () -> {
						assertThrows(IllegalArgumentException.class, () -> genotypeTester = new Genotype("bb><"));
					}, () -> {
						assertThrows(IllegalArgumentException.class, () -> genotypeTester = new Genotype("Bb*)CC"));

					});
				}

				@Test
				@DisplayName("Letter pairs don't match")
				// in the Animal Crossing Flower genes that we're dealing with having a gene
				// that looks like: Yw cannot exist
				// therefore in this program, pairs with mismatching letters are considered
				// Illegal arguments
				void testNonMatchingLetterPairs() {
					assertAll(() -> {
						assertThrows(IllegalArgumentException.class, () -> genotypeTester = new Genotype("Yw"));
					}, () -> {
						assertThrows(IllegalArgumentException.class, () -> genotypeTester = new Genotype("wx"));
					}, () -> {
						assertThrows(IllegalArgumentException.class, () -> genotypeTester = new Genotype("Yyij"));
					},

							() -> {
								assertThrows(IllegalArgumentException.class,
										() -> genotypeTester = new Genotype("PoLL"));
							}, () -> {
								assertThrows(IllegalArgumentException.class,
										() -> genotypeTester = new Genotype("CCwxbb"));
							}, () -> {
								assertThrows(IllegalArgumentException.class,
										() -> genotypeTester = new Genotype("IyyL"));
							},

							() -> {
								assertThrows(IllegalArgumentException.class,
										() -> genotypeTester = new Genotype("PpLoXb"));
							});
				}
			}
		}

		@Nested
		@DisplayName("Constructor: Integer Array of Gene Values")
		class ConstructorWithIntArrayInput {

			@Test
			@DisplayName("Creation: Single Gene")
			void testGenotypeCreationWithInt() {
				// an array of integers is passed in, these should be copied into the Genotype's
				// genes array

				assertAll(() -> {
					int[] arr = new int[] { 1 };
					genotypeTester = new Genotype(arr);
					assertNotNull(genotypeTester);
					int expectedValue = 1;
					char expectedMarker = 'a';
					assertEquals(expectedValue, genotypeTester.getGeneAtIndex(0));
					assertTrue(Character.isLetter(genotypeTester.getAlleleRepresentation(0)));
					assertEquals(expectedMarker, genotypeTester.getAlleleRepresentation(0));
				}, () -> {
					int[] arr = new int[] { 0 };
					genotypeTester = new Genotype(arr);
					assertNotNull(genotypeTester);
					int expectedValue = 0;
					char expectedMarker = 'a';
					assertEquals(expectedValue, genotypeTester.getGeneAtIndex(0));
					assertTrue(Character.isLetter(genotypeTester.getAlleleRepresentation(0)));
					assertEquals(expectedMarker, genotypeTester.getAlleleRepresentation(0));
				}, () -> {
					int[] arr = new int[] { 2 };
					genotypeTester = new Genotype(arr);
					assertNotNull(genotypeTester);
					int expectedValue = 2;
					char expectedMarker = 'a';
					assertEquals(expectedValue, genotypeTester.getGeneAtIndex(0));
					assertTrue(Character.isLetter(genotypeTester.getAlleleRepresentation(0)));
					assertEquals(expectedMarker, genotypeTester.getAlleleRepresentation(0));
				});
				// fail("Test not implemented!");
			}

			@Test
			@DisplayName("Creation: Multiple Genes")
			void testGenotypeCreationWithMultInts() {
				assertAll(() -> {
					int[] arr = new int[] { 1, 2, 0, 0 };
					genotypeTester = new Genotype(arr);
					assertNotNull(genotypeTester);
					int expectedValue = 1;
					int expectedValueAt1 = 2;
					int expectedValueAt2 = 0;
					int expectedValueAt3 = 0;
					char expectedMarkerAt0 = 'a';
					char expectedMarkerAt1 = 'b';
					char expectedMarkerAt2 = 'c';
					char expectedMarkerAt3 = 'd';
					assertEquals(expectedValue, genotypeTester.getGeneAtIndex(0));
					assertTrue(Character.isLetter(genotypeTester.getAlleleRepresentation(0)));
					assertEquals(expectedMarkerAt0, genotypeTester.getAlleleRepresentation(0));

					assertEquals(expectedValueAt1, genotypeTester.getGeneAtIndex(1));
					assertTrue(Character.isLetter(genotypeTester.getAlleleRepresentation(1)));
					assertEquals(expectedMarkerAt1, genotypeTester.getAlleleRepresentation(1));

					assertEquals(expectedValueAt2, genotypeTester.getGeneAtIndex(2));
					assertTrue(Character.isLetter(genotypeTester.getAlleleRepresentation(2)));
					assertEquals(expectedMarkerAt2, genotypeTester.getAlleleRepresentation(2));

					assertEquals(expectedValueAt3, genotypeTester.getGeneAtIndex(3));
					assertTrue(Character.isLetter(genotypeTester.getAlleleRepresentation(3)));
					assertEquals(expectedMarkerAt3, genotypeTester.getAlleleRepresentation(3));

				}, () -> {
					int[] arr = new int[] { 2, 2, 1, 1, 0, 2 };
					genotypeTester = new Genotype(arr);
					assertNotNull(genotypeTester);
					int expectedValue = 2;
					int expectedValueAt1 = 2;
					int expectedValueAt2 = 1;
					int expectedValueAt3 = 1;
					int expectedValueAt4 = 0;
					int expectedValueAt5 = 2;
					char expectedMarkerAt0 = 'a';
					char expectedMarkerAt1 = 'b';
					char expectedMarkerAt2 = 'c';
					char expectedMarkerAt3 = 'd';
					char expectedMarkerAt4 = 'e';
					char expectedMarkerAt5 = 'f';
					assertEquals(expectedValue, genotypeTester.getGeneAtIndex(0));
					assertTrue(Character.isLetter(genotypeTester.getAlleleRepresentation(0)));
					assertEquals(expectedMarkerAt0, genotypeTester.getAlleleRepresentation(0));

					assertEquals(expectedValueAt1, genotypeTester.getGeneAtIndex(1));
					assertTrue(Character.isLetter(genotypeTester.getAlleleRepresentation(1)));
					assertEquals(expectedMarkerAt1, genotypeTester.getAlleleRepresentation(1));

					assertEquals(expectedValueAt2, genotypeTester.getGeneAtIndex(2));
					assertTrue(Character.isLetter(genotypeTester.getAlleleRepresentation(2)));
					assertEquals(expectedMarkerAt2, genotypeTester.getAlleleRepresentation(2));

					assertEquals(expectedValueAt3, genotypeTester.getGeneAtIndex(3));
					assertTrue(Character.isLetter(genotypeTester.getAlleleRepresentation(3)));
					assertEquals(expectedMarkerAt3, genotypeTester.getAlleleRepresentation(3));

					assertEquals(expectedValueAt4, genotypeTester.getGeneAtIndex(4));
					assertTrue(Character.isLetter(genotypeTester.getAlleleRepresentation(4)));
					assertEquals(expectedMarkerAt4, genotypeTester.getAlleleRepresentation(4));

					assertEquals(expectedValueAt5, genotypeTester.getGeneAtIndex(5));
					assertTrue(Character.isLetter(genotypeTester.getAlleleRepresentation(5)));
					assertEquals(expectedMarkerAt5, genotypeTester.getAlleleRepresentation(5));
				});
			}

			@Test
			@DisplayName("Creation: More than 26 Genes")
			void testMoreThanTheAlphabet() {
				int[] arr = new int[28];
				genotypeTester = new Genotype(arr);
				// the whole array will be filled with 0s since its freshly initialized
				int expectedValue = 0;
				for (int i = 0; i < 28; i++) {
					assertEquals(expectedValue, genotypeTester.getGeneAtIndex(i));
					assertTrue(Character.isLetter(genotypeTester.getAlleleRepresentation(i)));
				}
				// we also want to check the geneMarkers
				char expectedAt25 = 'z';
				char expectedAt26 = 'a';
				char expectedAt27 = 'b';
				assertEquals(expectedAt25, genotypeTester.getAlleleRepresentation(25));
				assertEquals(expectedAt26, genotypeTester.getAlleleRepresentation(26));
				assertEquals(expectedAt27, genotypeTester.getAlleleRepresentation(27));
			}

			@Nested
			@DisplayName("Faulty Genotype Tests")
			class testFaultyGenotype {

				@Test
				@DisplayName("Invalid Allele value")
				void testInvalidAlleleValues() {
					assertAll(() -> {
						int[] arr = new int[] { 4 };
						assertThrows(IllegalArgumentException.class, () -> genotypeTester = new Genotype(arr));
					}, () -> {
						int[] arr = new int[] { -1 };
						assertThrows(IllegalArgumentException.class, () -> genotypeTester = new Genotype(arr));
					});

				}
			}

			@Test
			@DisplayName("Empty Sequence")
			void testEmptySequenceValueArray() {
				int[] arr = {};
				assertThrows(IllegalArgumentException.class, () -> genotypeTester = new Genotype(arr));

			}

		}
	}

	@Nested
	@DisplayName("Method Tests")
	class testMethods {

		@Nested
		@DisplayName("Single Gene Representation Tests")
		// we need to determine a series of tests using both constructor methods
		class testGeneRep {
			// for when there is only one
			@Test
			@DisplayName("Only one gene to test")
			void testOneGeneRepMethod() {
				assertAll(() -> {
					int[] arr = new int[] { 1 };
					genotypeTester = new Genotype(arr);
					assertNotNull(genotypeTester);
					String expected = "Aa";
					assertTrue(expected.equals(genotypeTester.getGeneRepresentation(0)));
				}, () -> {
					int[] arr = new int[] { 0 };
					genotypeTester = new Genotype(arr);
					assertNotNull(genotypeTester);
					String expected = "aa";
					assertTrue(expected.equals(genotypeTester.getGeneRepresentation(0)));

				}, () -> {
					int[] arr = new int[] { 2 };
					genotypeTester = new Genotype(arr);
					assertNotNull(genotypeTester);
					String expected = "AA";
					assertTrue(expected.equals(genotypeTester.getGeneRepresentation(0)));

				}, () -> {
					String expected = "Aa";
					genotypeTester = new Genotype(expected);
					assertNotNull(genotypeTester);

					assertTrue(expected.equals(genotypeTester.getGeneRepresentation(0)));

				}, () -> {
					String expected = "AA";
					genotypeTester = new Genotype(expected);
					assertNotNull(genotypeTester);
					assertTrue(expected.equals(genotypeTester.getGeneRepresentation(0)));

				}, () -> {

					String expected = "aa";
					genotypeTester = new Genotype(expected);
					assertNotNull(genotypeTester);
					assertTrue(expected.equals(genotypeTester.getGeneRepresentation(0)));

				}, () -> {

					String expected = "CC";
					genotypeTester = new Genotype(expected);
					assertNotNull(genotypeTester);
					assertTrue(expected.equals(genotypeTester.getGeneRepresentation(0)));

				});

			}

			// for trying to access an index that doesnt exist
			@Test
			@DisplayName("Accessing an invalid index")
			void testInvalidIndex() {
				assertAll(() -> {
					String expected = "";
					genotypeTester = new Genotype("aa");
					assertNotNull(genotypeTester);
					assertTrue(expected.equals(genotypeTester.getGeneRepresentation(-1)));
					assertTrue(expected.equals(genotypeTester.getGeneRepresentation(2)));

				}, () -> {
					String expected = "";
					int[] arr = { 1 };
					genotypeTester = new Genotype(arr);
					assertNotNull(genotypeTester);
					assertTrue(expected.equals(genotypeTester.getGeneRepresentation(-1)));
					assertTrue(expected.equals(genotypeTester.getGeneRepresentation(2)));

				}, () -> {
					String expected = "";
					int[] arr = { 1, 2, 1, 0, 0 };
					genotypeTester = new Genotype(arr);
					assertNotNull(genotypeTester);
					assertTrue(expected.equals(genotypeTester.getGeneRepresentation(-1)));
					assertTrue(expected.equals(genotypeTester.getGeneRepresentation(5)));

				}, () -> {
					String expected = "";
					genotypeTester = new Genotype("aaBbCCKkll");
					assertNotNull(genotypeTester);
					assertNotNull(genotypeTester);
					assertTrue(expected.equals(genotypeTester.getGeneRepresentation(-1)));
					assertTrue(expected.equals(genotypeTester.getGeneRepresentation(5)));

				});

				// fail("Test not implemented!");
			}

			// checking each individual gene in a set
			@Test
			@DisplayName("Checking a set of gene representations")
			void testGeneSet() {
				assertAll(() -> {
					String expectedAt0 = "aa";
					String expectedAt1 = "BB";
					String expectedAt2 = "Cc";
					String expectedAt3 = "Dd";
					String full = expectedAt0 + expectedAt1 + expectedAt2 + "dD";
					genotypeTester = new Genotype(full);
					assertNotNull(genotypeTester);
					assertTrue(expectedAt0.equals(genotypeTester.getGeneRepresentation(0)));
					assertTrue(expectedAt1.equals(genotypeTester.getGeneRepresentation(1)));
					assertTrue(expectedAt2.equals(genotypeTester.getGeneRepresentation(2)));
					assertTrue(expectedAt3.equals(genotypeTester.getGeneRepresentation(3)));

				}, () -> {
					String expectedAt0 = "Aa";
					String expectedAt1 = "bb";
					String expectedAt2 = "CC";
					int[] arr = { 1, 0, 2 };
					genotypeTester = new Genotype(arr);
					assertNotNull(genotypeTester);
					assertTrue(expectedAt0.equals(genotypeTester.getGeneRepresentation(0)));
					assertTrue(expectedAt1.equals(genotypeTester.getGeneRepresentation(1)));
					assertTrue(expectedAt2.equals(genotypeTester.getGeneRepresentation(2)));
				});

			}
		}

		@Nested
		@DisplayName("Full Gene Representation Tests")
		class testGeneRepAll {
			// for when there is only one gene
			@Test
			@DisplayName("Single Gene")
			void testGeneRepAllSingle() {
				assertAll(() -> {
					int[] arr = new int[] { 1 };
					genotypeTester = new Genotype(arr);
					assertNotNull(genotypeTester);
					String expected = "Aa";
					assertTrue(expected.equals(genotypeTester.getGeneRepresentation()));
				}, () -> {
					int[] arr = new int[] { 0 };
					genotypeTester = new Genotype(arr);
					assertNotNull(genotypeTester);
					String expected = "aa";
					assertTrue(expected.equals(genotypeTester.getGeneRepresentation()));

				}, () -> {
					int[] arr = new int[] { 2 };
					genotypeTester = new Genotype(arr);
					assertNotNull(genotypeTester);
					String expected = "AA";
					assertTrue(expected.equals(genotypeTester.getGeneRepresentation()));

				}, () -> {
					String expected = "Aa";
					genotypeTester = new Genotype(expected);
					assertNotNull(genotypeTester);
					assertTrue(expected.equals(genotypeTester.getGeneRepresentation()));

				}, () -> {
					String expected = "AA";
					genotypeTester = new Genotype(expected);
					assertNotNull(genotypeTester);
					assertTrue(expected.equals(genotypeTester.getGeneRepresentation()));

				}, () -> {

					String expected = "aa";
					genotypeTester = new Genotype(expected);
					assertNotNull(genotypeTester);
					assertTrue(expected.equals(genotypeTester.getGeneRepresentation()));

				}, () -> {

					String expected = "CC";
					genotypeTester = new Genotype(expected);
					assertNotNull(genotypeTester);
					assertTrue(expected.equals(genotypeTester.getGeneRepresentation()));

				});
			}

			// for when there are multiple genes
			@Test
			@DisplayName("Multiple Genes")
			void testGeneRepAllMult() {
				assertAll(() -> {
					assertAll(() -> {
						String expectedAt0 = "aa";
						String expectedAt1 = "BB";
						String expectedAt2 = "Cc";
						String expectedAt3 = "Dd";
						String full = expectedAt0 + expectedAt1 + expectedAt2 + "dD";
						String proper = expectedAt0 + expectedAt1 + expectedAt2 + expectedAt3;
						genotypeTester = new Genotype(full);
						assertNotNull(genotypeTester);
						assertTrue(proper.equals(genotypeTester.getGeneRepresentation()));
					}, () -> {
						String expectedAt0 = "Aa";
						String expectedAt1 = "bb";
						String expectedAt2 = "CC";
						String full = expectedAt0 + expectedAt1 + expectedAt2;
						int[] arr = { 1, 0, 2 };
						genotypeTester = new Genotype(arr);
						assertNotNull(genotypeTester);
						assertTrue(full.equals(genotypeTester.getGeneRepresentation()));
					});
				});
			}
		}

		@Test
		@DisplayName("Number Of Genes Method Test")
		void testNumberOfGenes() {
			assertAll(() -> {
				int length = 2;
				genotypeTester = new Genotype(new int[] { 1, 2 });
				assertEquals(length, genotypeTester.getNumberOfGenes());
			}, () -> {
				int length = 2;
				genotypeTester = new Genotype("AAbb");
				assertEquals(length, genotypeTester.getNumberOfGenes());
			}, () -> {
				int length = 4;
				genotypeTester = new Genotype("bbAANnBB");
				assertEquals(length, genotypeTester.getNumberOfGenes());
			}, () -> {
				int length = 1;
				genotypeTester = new Genotype("bb");
				assertEquals(length, genotypeTester.getNumberOfGenes());
			});
		}

		@Nested
		@DisplayName("Equals Method")
		class testEquals {

			@Nested
			@DisplayName("Equals with Construction: Integer Array")
			class testEqualsWithIntegerArray {

				@Test
				@DisplayName("Equal: Single Gene Comparison")
				void testSingleGeneSame() {

					assertAll(() -> {
						genotypeTester = new Genotype(new int[] { 1 });
						Genotype genotypeTester2 = new Genotype(new int[] { 1 });
						assertTrue(genotypeTester.equals(genotypeTester2));
						assertTrue(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 0 });
						Genotype genotypeTester2 = new Genotype(new int[] { 0 });
						assertTrue(genotypeTester.equals(genotypeTester2));
						assertTrue(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 2 });
						Genotype genotypeTester2 = new Genotype(new int[] { 2 });
						assertTrue(genotypeTester.equals(genotypeTester2));
						assertTrue(genotypeTester2.equals(genotypeTester));
					});
				}

				@Test
				@DisplayName("Unequal: Single Gene Comparison")
				void testSingleGeneDifferent() {
					assertAll(() -> {
						genotypeTester = new Genotype(new int[] { 2 });
						Genotype genotypeTester2 = new Genotype(new int[] { 1 });
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 0 });
						Genotype genotypeTester2 = new Genotype(new int[] { 1 });
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 2 });
						Genotype genotypeTester2 = new Genotype(new int[] { 0 });
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 1 });
						Genotype genotypeTester2 = new Genotype(new int[] { 2 });
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					});
				}

				@Test
				@DisplayName("Equal: Multiple Gene Comparison")
				void testMultipleGeneSame() {
					assertAll(() -> {
						genotypeTester = new Genotype(new int[] { 1, 2 });
						Genotype genotypeTester2 = new Genotype(new int[] { 1, 2 });
						assertTrue(genotypeTester.equals(genotypeTester2));
						assertTrue(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 0, 2, 2, 1 });
						Genotype genotypeTester2 = new Genotype(new int[] { 0, 2, 2, 1 });
						assertTrue(genotypeTester.equals(genotypeTester2));
						assertTrue(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 2, 0 });
						Genotype genotypeTester2 = new Genotype(new int[] { 2, 0 });
						assertTrue(genotypeTester.equals(genotypeTester2));
						assertTrue(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 2, 0, 1, 1, 0 });
						Genotype genotypeTester2 = new Genotype(new int[] { 2, 0, 1, 1, 0 });
						assertTrue(genotypeTester.equals(genotypeTester2));
						assertTrue(genotypeTester2.equals(genotypeTester));
					});

				}

				@Test
				@DisplayName("Unequal: Multiple Gene Comparison")
				void testMultipleGeneDifferent() {
					assertAll(() -> {
						genotypeTester = new Genotype(new int[] { 2, 2 });
						Genotype genotypeTester2 = new Genotype(new int[] { 1, 0 });
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 0, 1, 2 });
						Genotype genotypeTester2 = new Genotype(new int[] { 0, 2, 2 });
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 2, 0 });
						Genotype genotypeTester2 = new Genotype(new int[] { 0, 2 });
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 1, 1, 1 });
						Genotype genotypeTester2 = new Genotype(new int[] { 2, 1, 1 });
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 1, 1, 0 });
						Genotype genotypeTester2 = new Genotype(new int[] { 1, 1, 1 });
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					});
				}

				@Test
				@DisplayName("Unequal: Different Gene Amounts")
				void testUnevenGenes() {
					assertAll(() -> {
						genotypeTester = new Genotype(new int[] { 2 });
						Genotype genotypeTester2 = new Genotype(new int[] { 1, 2 });
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 0, 2, 1 });
						Genotype genotypeTester2 = new Genotype(new int[] { 0, 2 });
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 2, 1, 1, 1 });
						Genotype genotypeTester2 = new Genotype(new int[] { 0 });
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 0, 1, 1 });
						Genotype genotypeTester2 = new Genotype(new int[] { 2, 1 });
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					});

				}

				@Test
				@DisplayName("Unequal: Empty Object")
				void testEmptyComparison() {
					genotypeTester = new Genotype(new int[] { 1, 2 });
					assertFalse(genotypeTester.equals(null));
				}

				@Test
				@DisplayName("Unequal: Non-Genotype Object")
				void testObjectNotGenotype() {
					genotypeTester = new Genotype(new int[] { 1, 2 });
					String differentObject = "hello";
					assertFalse(genotypeTester.equals(differentObject));
				}

			}

			@Nested
			@DisplayName("Equals with Construction: String Input")
			class testEqualsWithStringInput {
				@Test
				@DisplayName("Equal: Single Gene Comparison")
				void testSingleGeneSame() {

					assertAll(() -> {
						genotypeTester = new Genotype("AA");
						Genotype genotypeTester2 = new Genotype("AA");
						assertTrue(genotypeTester.equals(genotypeTester2));
						assertTrue(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype("Bb");
						Genotype genotypeTester2 = new Genotype("Bb");
						assertTrue(genotypeTester.equals(genotypeTester2));
						assertTrue(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype("Bb");
						Genotype genotypeTester2 = new Genotype("bB");
						assertTrue(genotypeTester.equals(genotypeTester2));
						assertTrue(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype("aa");
						Genotype genotypeTester2 = new Genotype("aa");
						assertTrue(genotypeTester.equals(genotypeTester2));
						assertTrue(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype("Bb");
						Genotype genotypeTester2 = new Genotype("Cc");
						assertTrue(genotypeTester.equals(genotypeTester2));
						assertTrue(genotypeTester2.equals(genotypeTester));
					});
				}

				@Test
				@DisplayName("Unequal: Single Gene Comparison")
				void testSingleGeneDifferent() {
					assertAll(() -> {
						genotypeTester = new Genotype("AA");
						Genotype genotypeTester2 = new Genotype("aa");
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype("Aa");
						Genotype genotypeTester2 = new Genotype("bb");
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype("aa");
						Genotype genotypeTester2 = new Genotype("Aa");
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype("Aa");
						Genotype genotypeTester2 = new Genotype("aa");
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					});
				}

				@Test
				@DisplayName("Equal: Multiple Gene Comparison")
				void testMultipleGeneSame() {
					assertAll(() -> {
						genotypeTester = new Genotype("AAbbCcDD");
						Genotype genotypeTester2 = new Genotype("AAbbCcDD");
						assertTrue(genotypeTester.equals(genotypeTester2));
						assertTrue(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype("AaRR");
						Genotype genotypeTester2 = new Genotype("AaRR");
						assertTrue(genotypeTester.equals(genotypeTester2));
						assertTrue(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype("ccAAbbBb");
						Genotype genotypeTester2 = new Genotype("ccAAbbBb");
						assertTrue(genotypeTester.equals(genotypeTester2));
						assertTrue(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype("rrWw");
						Genotype genotypeTester2 = new Genotype("rrWw");
						assertTrue(genotypeTester.equals(genotypeTester2));
						assertTrue(genotypeTester2.equals(genotypeTester));
					});

				}

				@Test
				@DisplayName("Unequal: Multiple Gene Comparison")
				void testMultipleGeneDifferent() {
					assertAll(() -> {
						genotypeTester = new Genotype("aaRr");
						Genotype genotypeTester2 = new Genotype("aaRR");
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype("ccAAbB");
						Genotype genotypeTester2 = new Genotype("CcAAbB");
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype("AallMM");
						Genotype genotypeTester2 = new Genotype("Aallmm");
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype("mmccaa");
						Genotype genotypeTester2 = new Genotype("mmccAa");
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype("AaJJAa");
						Genotype genotypeTester2 = new Genotype("AAJJAa");
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					});
				}

				@Test
				@DisplayName("Unequal: Different Gene Amounts")
				void testUnevenGenes() {
					assertAll(() -> {
						genotypeTester = new Genotype("Aa");
						Genotype genotypeTester2 = new Genotype("AaDD");
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype("ccAAbBSS");
						Genotype genotypeTester2 = new Genotype("ccAAbB");
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					});

				}

				@Test
				@DisplayName("Unequal: Empty Object")
				void testEmptyComparison() {
					genotypeTester = new Genotype("AA");
					assertFalse(genotypeTester.equals(null));
				}

				@Test
				@DisplayName("Unequal: Non-Genotype Object")
				void testObjectNotGenotype() {
					genotypeTester = new Genotype("AAaa");
					String differentObject = "AAaa";
					assertFalse(genotypeTester.equals(differentObject));
				}
			}

			@Nested
			@DisplayName("Equals with Different Constructions")
			class testEqualsWithDifferentConstructors {
				@Test
				@DisplayName("Equal: Single Gene Comparison")
				void testSingleGeneSame() {

					assertAll(() -> {
						genotypeTester = new Genotype(new int[] { 1 });
						Genotype genotypeTester2 = new Genotype("Aa");
						assertTrue(genotypeTester.equals(genotypeTester2));
						assertTrue(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 0 });
						Genotype genotypeTester2 = new Genotype("bb");
						assertTrue(genotypeTester.equals(genotypeTester2));
						assertTrue(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 2 });
						Genotype genotypeTester2 = new Genotype("RR");
						assertTrue(genotypeTester.equals(genotypeTester2));
						assertTrue(genotypeTester2.equals(genotypeTester));
					});
				}

				@Test
				@DisplayName("Unequal: Single Gene Comparison")
				void testSingleGeneDifferent() {
					assertAll(() -> {
						genotypeTester = new Genotype(new int[] { 2 });
						Genotype genotypeTester2 = new Genotype("Bb");
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 0 });
						Genotype genotypeTester2 = new Genotype("AA");
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 2 });
						Genotype genotypeTester2 = new Genotype("ss");
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 1 });
						Genotype genotypeTester2 = new Genotype("JJ");
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					});
				}

				@Test
				@DisplayName("Equal: Multiple Gene Comparison")
				void testMultipleGeneSame() {
					assertAll(() -> {
						genotypeTester = new Genotype(new int[] { 1, 2 });
						Genotype genotypeTester2 = new Genotype("AaBB");
						assertTrue(genotypeTester.equals(genotypeTester2));
						assertTrue(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 0, 2, 1, 1 });
						Genotype genotypeTester2 = new Genotype("ooMMOoYy ");
						assertTrue(genotypeTester.equals(genotypeTester2));
						assertTrue(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 2, 0 });
						Genotype genotypeTester2 = new Genotype("MMaa");
						assertTrue(genotypeTester.equals(genotypeTester2));
						assertTrue(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 2, 0, 1, 1, 0 });
						Genotype genotypeTester2 = new Genotype("JJiiMmNnaa");
						assertTrue(genotypeTester.equals(genotypeTester2));
						assertTrue(genotypeTester2.equals(genotypeTester));
					});

				}

				@Test
				@DisplayName("Unequal: Multiple Gene Comparison")
				void testMultipleGeneDifferent() {
					assertAll(() -> {
						genotypeTester = new Genotype(new int[] { 2, 2 });
						Genotype genotypeTester2 = new Genotype("AAAa");
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 0, 1, 2 });
						Genotype genotypeTester2 = new Genotype("aaIiMm");
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 2, 0 });
						Genotype genotypeTester2 = new Genotype("aaMM");
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 1, 1, 1 });
						Genotype genotypeTester2 = new Genotype("aaBbSs");
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 1, 1, 0 });
						Genotype genotypeTester2 = new Genotype("GgXXbb");
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					});
				}

				@Test
				@DisplayName("Unequal: Different Gene Amounts")
				void testUnevenGenes() {
					assertAll(() -> {
						genotypeTester = new Genotype(new int[] { 2 });
						Genotype genotypeTester2 = new Genotype("AAbb");
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype("ooPPEe");
						Genotype genotypeTester2 = new Genotype(new int[] { 0, 2 });
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 2, 1, 1, 1 });
						Genotype genotypeTester2 = new Genotype("AA");
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					}, () -> {
						genotypeTester = new Genotype(new int[] { 0, 1, 1 });
						Genotype genotypeTester2 = new Genotype("nnOo");
						assertFalse(genotypeTester.equals(genotypeTester2));
						assertFalse(genotypeTester2.equals(genotypeTester));
					});

				}

			}

		}
	}
}