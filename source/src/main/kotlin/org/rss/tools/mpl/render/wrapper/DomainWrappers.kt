package org.rss.tools.mpl.render.wrapper

import org.rss.tools.mpl.domain.Section
import org.rss.tools.mpl.domain.element.*
import org.rss.tools.mpl.render.DocumentVisitor
import org.rss.tools.mpl.render.Rendered

/**
 * Creates a visitable class for each type of element, in order to use overloaded visit() correctly.
 */

/**
 */
class SectionDecorator(private val wrapped: Section) : Rendered {

    override fun accept(visitor: DocumentVisitor) {
        visitor.visit(this.wrapped)
    }
}

class CheckboxDecorator(private val wrapped: Checkbox) : Rendered {

    override fun accept(visitor: DocumentVisitor) {
        visitor.visit(this.wrapped)
    }
}

class ComboboxDecorator(private val wrapped: Combobox) : Rendered {

    override fun accept(visitor: DocumentVisitor) {
        visitor.visit(this.wrapped)
    }
}

class RadioboxDecorator(private val wrapped: Radiobox) : Rendered {

    override fun accept(visitor: DocumentVisitor) {
        visitor.visit(this.wrapped)
    }
}

class LabelDecorator(private val wrapped: Label) : Rendered {

    override fun accept(visitor: DocumentVisitor) {
        visitor.visit(this.wrapped)
    }
}

class ButtonDecorator(private val wrapped: Button) : Rendered {

    override fun accept(visitor: DocumentVisitor) {
        visitor.visit(this.wrapped)
    }
}

class InputEmailDecorator(private val wrapped: InputEmail) : Rendered {

    override fun accept(visitor: DocumentVisitor) {
        visitor.visit(this.wrapped)
    }
}

class InputSecretDecorator(private val wrapped: InputSecret) : Rendered {

    override fun accept(visitor: DocumentVisitor) {
        visitor.visit(this.wrapped)
    }
}

class InputTextDecorator(private val wrapped: InputText) : Rendered {

    override fun accept(visitor: DocumentVisitor) {
        visitor.visit(this.wrapped)
    }
}

class LineHeaderDecorator(private val wrapped: LineHeader) : Rendered {

    override fun accept(visitor: DocumentVisitor) {
        visitor.visit(this.wrapped)
    }
}

class ListItemDecorator(private val wrapped: ListItem) : Rendered {

    override fun accept(visitor: DocumentVisitor) {
        visitor.visit(this.wrapped)
    }
}

class TableDecorator(private val wrapped: Table) : Rendered {

    override fun accept(visitor: DocumentVisitor) {
        visitor.visit(this.wrapped)
    }
}