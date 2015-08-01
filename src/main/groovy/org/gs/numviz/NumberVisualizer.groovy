package org.gs.numviz

import org.gs.numviz.numbers.SpecialNumber

import java.util.logging.Logger

// see end-of-file for license information

/**
 * Coordinates "business logic" of number visualization.
 */
class NumberVisualizer {

    // what number to show
    final private SpecialNumber NUMBER

    // how many pairs of digits to show in visualization?
    final private Integer NR_OF_CONNECTIONS_TO_SHOW

    // some circle properties:
    private float radius

    // segments to attach connections to...
    // every segment instance corresponds to one digit
    public List<Segment> segment

    // some properties of (all) segments
    // over what angle does a segment extend
    final Double SEGMENT_EXTEND_ANGLE_DEG = 30
    final Double SEGMENT_EXTEND_ANGLE = Math.toRadians(SEGMENT_EXTEND_ANGLE_DEG)
    final Double SEGMENT_PADDING_ANGLE_DEG = 3
    // what's the distance to the next segment
    final Double SEGMENT_PADDING_ANGLE = Math.toRadians(SEGMENT_PADDING_ANGLE_DEG)

    final Integer MARGIN = 20

    private static final Logger LOGGER = Logger.getLogger(NumberVisualizer.class.getName())


    public NumberVisualizer( SpecialNumber NUMBER, Integer NR_OF_CONNECTIONS_TO_SHOW, Integer resolution) {

        // the most important property: what number shall we visualize?
        this.NUMBER = NUMBER

        this.NR_OF_CONNECTIONS_TO_SHOW = NR_OF_CONNECTIONS_TO_SHOW

        this.radius = (resolution- 2*MARGIN).intdiv(2)

    }

    /**
     * initialize the segments
     */
    public void initSegments() {

        segment = new ArrayList<Segment>(10)

        (0..9).each { thisDigit ->
            segment[thisDigit] = new Segment(
                    digit: thisDigit,
                    nrOfOccurences: NUMBER.countDigit(thisDigit),
                    color: NumVizColor.color[thisDigit],
                    radius: this.radius,
                    // TODO: adjust angleStart, so that segment 0 starts at top
                    angleStart: thisDigit * ((SEGMENT_EXTEND_ANGLE + (2 * SEGMENT_PADDING_ANGLE))),
                    angleExtend: SEGMENT_EXTEND_ANGLE)

            // create digiNodes (== lineEndings) only if this digit occurs once or more
            if (segment[thisDigit].nrOfOccurences > 0) {
                segment[thisDigit].with {

                    // LOGGER.info "setting up ${nrOfOccurences} digiNodes for Segment[${thisDigit}]: angleStart=${angleStart}, angleExtend=${angleExtend} and radius=${radius}"
                    setUpDigiNodes()
                }
            }

        }

    }
}
