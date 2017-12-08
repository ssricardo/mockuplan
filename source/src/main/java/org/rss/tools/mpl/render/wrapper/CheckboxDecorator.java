package org.rss.tools.mpl.render.wrapper;

import org.rss.tools.mpl.domain.element.Checkbox;
import org.rss.tools.mpl.render.DocumentVisitor;
import org.rss.tools.mpl.render.Rendered;

public class CheckboxDecorator implements Rendered {

	private Checkbox wrapped;

	public CheckboxDecorator(Checkbox source) {
		this.wrapped = source;
	}

	public void accept(DocumentVisitor visitor) {
		visitor.visit(this.wrapped);
	}
}