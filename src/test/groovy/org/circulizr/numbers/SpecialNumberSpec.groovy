package org.circulizr.numbers

import spock.lang.Specification
import spock.lang.Unroll

// see end-of-file for license information


class SpecialNumberSpec extends Specification {

    SpecialNumber number

    def "pi is a nice and special number"() {
        when:
            number = new Pi( 5 ) // 3.1415

        then:
            number.getDigit( 0 ) == 3
            number.getDigit( 1 ) == 1

            number.countDigit( 3 ) == 1 // occurs once
            number.countDigit( 1 ) == 2 // occurs twice
            number.countDigit( 2 ) == 0 // does not occur
    }

    /**
     *
     * @param precision
     * @param digit
     * @param count
     * @return
     */
    def "get all pairs"() {
        when:
        number = new Pi(3) // 3.14

        then:
        number.getAllPairsUpTo(1) == [new Pair(3,1), new Pair(1,4)]

        when:
        number = new Pi(5) // 3.1415

        then:
        number.NUMBER_OF_DIGITS == 5
        number.getAllPairsUpTo( 3 ) == [new Pair(3,1), new Pair(1,4), new Pair( 4,1), new Pair(1,5)]
    }

    /**
     * count occurences of digits in pairs. E.g. in "3.14", 1 occurs in two pairs
     */
    @Unroll
    def "count occurence of digit in first two pairs"() {
        when:
        number = new Pi(3) // 3.14

        then:
            // 3 occurs in one pair
            number.countOccurencesInPairs( 3, 1) == 1

            // 1 occurs in two pairs
            number.countOccurencesInPairs( 1, 1) == 2

            // 4 occurs in one pair
            number.countOccurencesInPairs( 4, 1) == 1

            // 5 does not occur
            number.countOccurencesInPairs( 5,1)  == 0

    }

    def "count occurence in first N pairs"() {
        when:
        number = new Pi(11) // 3.1415926535

        then:
        // 3 occurs in three pairs
           number.countOccurencesInPairs( 3, 9) == 3

           number.countOccurencesInPairs( 5, 9) == 5 // 5 pairs

         // 1 occures once in first pair
           number.countOccurencesInPairs( 1, 0) == 1

         //... and again in second pair
           number.countOccurencesInPairs( 1, 1) == 2



    }


    @Unroll
    def "count digits works for different precisions of PI"(int precision, int digit, int count) {
        when:
            number = new Pi( precision )

        then:
            number.countDigit(digit) == count

        where:
        precision | digit | count
        // Pi(1) == 3
        1         |   3   | 1
        1         |   1   | 0
        1         |   2   | 0
        1         |   4   | 0
        1         |   5   | 0
        1         |   6   | 0
        1         |   7   | 0
        1         |   8   | 0
        1         |   9   | 0
        1         |   0   | 0

        // Pi(5) == 3.1415
        5         |   1   | 2
        5         |   2   | 0
        5         |   3   | 1
        5         |   4   | 1
        5         |   5   | 1
        5         |   6   | 0
        5         |   7   | 0
        5         |   8   | 0
        5         |   9   | 0
        5         |   0   | 0

        // Pi(10) == 3.141592653
        10        |   1   | 2
        10        |   2   | 1
        10        |   3   | 2
        10        |   4   | 1
        10        |   5   | 2
        10        |   6   | 1
        10        |   7   | 0
        10        |   8   | 0
        10        |   9   | 1
        10        |   0   | 0


    }
}