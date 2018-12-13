package org.rss.tools.mpl.render

import org.rss.tools.mpl.domain.Document
import org.rss.tools.mpl.domain.Element
import org.rss.tools.mpl.domain.Section
import org.rss.tools.mpl.domain.element.*
import org.rss.tools.mpl.render.wrapper.*

import java.lang.reflect.InvocationTargetException
import java.util.HashMap

/**
 * Base class for renderers of [Document].
 * Contains the relation between [Document] classes and [Rendered].
 * @author ricardo saturnino
 */
abstract class BaseDocumentRenderer {

    protected abstract val visitor: DocumentVisitor

    protected fun findVisitDecorated(element: Element) {
        val rendererClass = DOMAIN_RENDERED_MAP[element.javaClass]
        try {
            val decorated = rendererClass?.getConstructor(element.javaClass)?.newInstance(element)
                    ?: throw IllegalArgumentException("Decorator not found")
            decorated.accept(visitor)
        } catch (e: InstantiationException) {
            throw IllegalStateException(e)
        } catch (e: IllegalAccessException) {
            throw IllegalStateException(e)
        } catch (e: IllegalArgumentException) {
            throw IllegalStateException(e)
        } catch (e: InvocationTargetException) {
            throw IllegalStateException(e)
        } catch (e: NoSuchMethodException) {
            throw IllegalStateException(e)
        } catch (e: SecurityException) {
            throw IllegalStateException(e)
        }

    }

    companion object {

        protected val DOMAIN_RENDERED_MAP: MutableMap<Class<out Element>, Class<out Rendered>> = HashMap()

        init {
            DOMAIN_RENDERED_MAP[Section::class.java] = SectionDecorator::class.java
            DOMAIN_RENDERED_MAP[Button::class.java] = ButtonDecorator::class.java
            DOMAIN_RENDERED_MAP[Checkbox::class.java] = CheckboxDecorator::class.java
            DOMAIN_RENDERED_MAP[Combobox::class.java] = ComboboxDecorator::class.java
            DOMAIN_RENDERED_MAP[InputEmail::class.java] = InputEmailDecorator::class.java
            DOMAIN_RENDERED_MAP[InputText::class.java] = InputTextDecorator::class.java
            DOMAIN_RENDERED_MAP[InputSecret::class.java] = InputSecretDecorator::class.java
            DOMAIN_RENDERED_MAP[Label::class.java] = LabelDecorator::class.java
            DOMAIN_RENDERED_MAP[LineHeader::class.java] = LineHeaderDecorator::class.java
            DOMAIN_RENDERED_MAP[ListItem::class.java] = ListItemDecorator::class.java
            DOMAIN_RENDERED_MAP[Radiobox::class.java] = RadioboxDecorator::class.java
            DOMAIN_RENDERED_MAP[Table::class.java] = TableDecorator::class.java
        }
    }
}
