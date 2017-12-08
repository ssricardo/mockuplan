package org.rss.tools.mpl.render.wrapper;

import org.rss.tools.mpl.domain.Section;
import org.rss.tools.mpl.render.DocumentVisitor;
import org.rss.tools.mpl.render.Rendered;

/**
 * @see
 * 	org.rss.tools.mpl.render.wrapper
 * @author ricardo
 */
public class SectionDecorator implements Rendered {

	private Section wrapped;

	public SectionDecorator(Section wrapped) {
		super();
		this.wrapped = wrapped;
	}

	@Override
	public void accept(DocumentVisitor visitor) {
		visitor.visit(this.wrapped);
	}

}
