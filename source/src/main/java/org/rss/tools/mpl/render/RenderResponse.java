package org.rss.tools.mpl.render;

public class RenderResponse {

	private ResponseType type;
	
	private Object data;

	public RenderResponse() {
		super();
	}

	public RenderResponse(ResponseType type, Object data) {
		this.type = type;
		this.data = data;
	}

	public ResponseType getType() {
		return type;
	}

	public void setType(ResponseType type) {
		this.type = type;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
