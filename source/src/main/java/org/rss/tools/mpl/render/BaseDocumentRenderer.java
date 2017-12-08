package org.rss.tools.mpl.render;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.rss.tools.mpl.domain.Document;
import org.rss.tools.mpl.domain.Element;
import org.rss.tools.mpl.domain.Section;
import org.rss.tools.mpl.domain.element.Button;
import org.rss.tools.mpl.domain.element.Checkbox;
import org.rss.tools.mpl.domain.element.Combobox;
import org.rss.tools.mpl.domain.element.InputEmail;
import org.rss.tools.mpl.domain.element.InputSecret;
import org.rss.tools.mpl.domain.element.InputText;
import org.rss.tools.mpl.domain.element.Label;
import org.rss.tools.mpl.domain.element.LineHeader;
import org.rss.tools.mpl.domain.element.ListItem;
import org.rss.tools.mpl.domain.element.Radiobox;
import org.rss.tools.mpl.domain.element.Table;
import org.rss.tools.mpl.render.wrapper.ButtonDecorator;
import org.rss.tools.mpl.render.wrapper.CheckboxDecorator;
import org.rss.tools.mpl.render.wrapper.ComboboxDecorator;
import org.rss.tools.mpl.render.wrapper.InputEmailDecorator;
import org.rss.tools.mpl.render.wrapper.InputSecretDecorator;
import org.rss.tools.mpl.render.wrapper.InputTextDecorator;
import org.rss.tools.mpl.render.wrapper.LabelDecorator;
import org.rss.tools.mpl.render.wrapper.LineHeaderDecorator;
import org.rss.tools.mpl.render.wrapper.ListItemDecorator;
import org.rss.tools.mpl.render.wrapper.RadioboxDecorator;
import org.rss.tools.mpl.render.wrapper.SectionDecorator;
import org.rss.tools.mpl.render.wrapper.TableDecorator;

/**
 * Base class for renderers of {@link Document}.  
 * Contains the relation between {@link Document} classes and {@link Rendered}.
 * @author ricardo saturnino
 *
 */
public abstract class BaseDocumentRenderer {

	protected static final Map<Class<? extends Element>, Class<? extends Rendered>> DOMAIN_RENDERED_MAP = new HashMap<>();

	static {
		DOMAIN_RENDERED_MAP.put(Section.class, SectionDecorator.class);
		DOMAIN_RENDERED_MAP.put(Button.class, ButtonDecorator.class);
		DOMAIN_RENDERED_MAP.put(Checkbox.class, CheckboxDecorator.class);
		DOMAIN_RENDERED_MAP.put(Combobox.class, ComboboxDecorator.class);
		DOMAIN_RENDERED_MAP.put(InputEmail.class, InputEmailDecorator.class);
		DOMAIN_RENDERED_MAP.put(InputText.class, InputTextDecorator.class);
		DOMAIN_RENDERED_MAP.put(InputSecret.class, InputSecretDecorator.class);
		DOMAIN_RENDERED_MAP.put(Label.class, LabelDecorator.class);
		DOMAIN_RENDERED_MAP.put(LineHeader.class, LineHeaderDecorator.class);
		DOMAIN_RENDERED_MAP.put(ListItem.class, ListItemDecorator.class);
		DOMAIN_RENDERED_MAP.put(Radiobox.class, RadioboxDecorator.class);
		DOMAIN_RENDERED_MAP.put(Table.class, TableDecorator.class);
	}

	protected void findVisitDecorated(Element element) {
		Class<? extends Rendered> rendererClass = DOMAIN_RENDERED_MAP.get(element.getClass());
		try {
			Rendered decorated = rendererClass.getConstructor(element.getClass()).newInstance(element);
			if (decorated == null) {
				throw new IllegalArgumentException("Decorator not found");
			}
			decorated.accept(getVisitor());
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new IllegalStateException(e);
		}
	}
	
	protected abstract DocumentVisitor getVisitor();
}
