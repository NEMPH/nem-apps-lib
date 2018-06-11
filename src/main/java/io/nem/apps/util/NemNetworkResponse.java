package io.nem.apps.util;

import java.io.Serializable;



/**
 * The Class NemNetworkResponse.
 */
public class NemNetworkResponse implements Serializable {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Flag that indicates whether this response is an error or not.
	 */
	private boolean error;

	/**
	 * The JSON response from the targeted platform server.
	 */
	private String response;

	/**
	 * Gets the {@link #error}.
	 *
	 * @return the {@link #error}.
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * Sets the {@link #error}.
	 *
	 * @param error
	 *            the {@link #error} to set.
	 */
	public void setError(boolean error) {
		this.error = error;
	}

	/**
	 * Gets the {@link #response}.
	 *
	 * @return the {@link #response}.
	 */
	public String getResponse() {
		return response;
	}

	/**
	 * Sets the {@link #response}.
	 *
	 * @param response
	 *            the {@link #response} to set.
	 */
	public void setResponse(String response) {
		this.response = response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (error ? 1231 : 1237);
		result = prime * result
				+ ((response == null) ? 0 : response.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NemNetworkResponse other = (NemNetworkResponse) obj;
		if (error != other.error)
			return false;
		if (response == null) {
			if (other.response != null)
				return false;
		} else if (!response.equals(other.response))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BotMillNetworkResponse [error=" + error + ", response="
				+ response + "]";
	}

}
