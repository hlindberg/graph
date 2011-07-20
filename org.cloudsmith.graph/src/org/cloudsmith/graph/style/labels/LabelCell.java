/**
 * Copyright (c) 2006-2011 Cloudsmith Inc. and other contributors, as listed below.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Cloudsmith
 * 
 */
package org.cloudsmith.graph.style.labels;

import org.cloudsmith.graph.IGraphElement;
import org.cloudsmith.graph.graphcss.StyleSet;
import org.cloudsmith.graph.style.IStyle;
import org.cloudsmith.graph.style.Span;

import com.google.common.base.Function;

/**
 * Data for label format.
 */
public class LabelCell {
	private Function<IGraphElement, String> valueFunc;

	private Function<IGraphElement, String> styleClassFunc;

	private final Span span;

	private StyleSet instanceStyles;

	/**
	 * @param styleClass2
	 * @param f
	 */
	public LabelCell(Function<IGraphElement, String> styleClass, Function<IGraphElement, String> value) {
		this.valueFunc = value;
		this.styleClassFunc = styleClass;
		this.span = Span.SPAN_1x1;
	}

	public LabelCell(Function<IGraphElement, String> styleClass, Function<IGraphElement, String> value, Span span) {
		this.valueFunc = value;
		this.styleClassFunc = styleClass;
		this.span = span;
	}

	public Span getSpan() {
		return span;
	}

	public String getStyleClass(IGraphElement ge) {
		return styleClassFunc.apply(ge);
	}

	public StyleSet getStyles() {
		return instanceStyles;
	}

	public String getValue(IGraphElement ge) {
		return valueFunc.apply(ge);
	}

	public LabelCell withStyle(IStyle<?> style) {
		return withStyles(style);
	}

	/**
	 * Convenience method to set instance styles on a LabelCell.
	 * It is illegal to call this method more than once.
	 * 
	 * @param styles
	 * @return the LabelCell
	 */
	public LabelCell withStyles(IStyle<?>... styles) {
		if(instanceStyles != null)
			throw new IllegalArgumentException("LabelCell's instance style is immutable once set");
		StyleSet styleMap = new StyleSet();
		for(IStyle<?> s : styles)
			styleMap.put(s);
		instanceStyles = styleMap;

		return this;
	}

}
