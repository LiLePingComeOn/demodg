package com.example.demo.entity;

public class OvlsResult {
		private int status; //状态
		private String msg;  //返回信息
		private Object data; // 返回数据
		
		
		public Object getData() {
			return data;
		}
		public void setData(Object data) {
			this.data = data;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}

	@Override
	public String toString() {
		return "OvlsResult{" +
				"status=" + status +
				", msg='" + msg + '\'' +
				", data=" + data +
				'}';
	}
}
