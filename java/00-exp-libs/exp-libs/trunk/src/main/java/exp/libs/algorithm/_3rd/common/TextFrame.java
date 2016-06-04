package exp.libs.algorithm._3rd.common;

/* TextFrame.java */

import java.awt.*;
import java.net.*;

/**
 * This <code>TextFrame</code> class brings up a frame containing the text
 * panel. The class constructor is normally called during the initialization of
 * of a <code>AlgAnimFrame</code> instance.
 * 
 * @see TextPanel
 */
public class TextFrame extends Frame {
    private TextPanel tp;
    private Scrollbar vScrollbar;

    /**
     * Construct a text frame and initialize the text panel to be added to
     * the text frame. By default, this frame is going to be displayed
     * with its top-left corner at position (600,0). This can be changed
     * in the constructor according to preference.
     * @param sourceURL The URL of the algorithm source code to be display
     * on the text panel.
     */
    public TextFrame( URL sourceURL ) {
	setLayout(new BorderLayout());
	move(600, 0);
	setTitle("Source Code");
	tp = new TextPanel( sourceURL );
	vScrollbar = new Scrollbar(Scrollbar.VERTICAL);
	vScrollbar.setValues(0, 10, 0, 90);
	vScrollbar.setPageIncrement(10);
	add("Center", tp);
	add("East", vScrollbar);
        pack();
        validate();
	//if (tp.getNumLines() == 0)
	    hide();
	//else
            //show();
    } // init()

    /**
     * Return the preferred size of the frame.
     * @return The frame size as an object of type Dimension.
     */
    public Dimension preferredSize() {
        return new Dimension(320,450);
    }

    /**
     * An event handler for the text frame which handles the WINDOW_DESTROY
     * event and vertical scrollbar.
     */
    public boolean handleEvent(Event event) {
        if (event.id == Event.WINDOW_DESTROY) {
            dispose();
        } else if (event.target == vScrollbar) {
            switch (event.id) {
                case Event.SCROLL_LINE_UP:
                case Event.SCROLL_LINE_DOWN:
                case Event.SCROLL_PAGE_UP:
                case Event.SCROLL_PAGE_DOWN:
                case Event.SCROLL_ABSOLUTE:
                    int val = ((Scrollbar)(event.target)).getValue();
                    int min = ((Scrollbar)(event.target)).getMinimum();
                    int max = ((Scrollbar)(event.target)).getMaximum();
                    tp.setStart(val*tp.getNumLines()/(max - min + 10));
                    //System.out.println(((Integer)event.arg).intValue());
                    tp.repaint();
	    }
  	}
        return super.handleEvent(event);
    }

    /**
     * Get the text panel corresponding to this frame.
     * @return The text panel contains in this frame.
     * @see TextPanel
     */
    public TextPanel getTextPanel() {
	return tp;
    }

    /**
     * Get the vertical scrollbar governing the view window of the text panel.
     * @return The vertical scrollbar.
     * @see TextPanel
     */
    public Scrollbar getVertScrollbar() {
	return vScrollbar;
    }
} // class TextFrame
