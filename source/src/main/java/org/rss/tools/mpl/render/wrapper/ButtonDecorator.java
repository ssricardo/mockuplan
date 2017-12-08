package org.rss.tools.mpl.render.wrapper;

import org.rss.tools.mpl.domain.element.Button;
import org.rss.tools.mpl.render.DocumentVisitor;
import org.rss.tools.mpl.render.Rendered;

public class ButtonDecorator implements Rendered {

	private Button wrapped;

	public ButtonDecorator(Button source) {
		this.wrapped = source;
	}

	public void accept(DocumentVisitor visitor) {
		visitor.visit(this.wrapped);
	}
}