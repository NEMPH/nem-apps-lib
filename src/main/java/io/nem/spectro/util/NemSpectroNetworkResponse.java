/*
 * MIT License
 *
 * Copyright (c) 2016 BotMill.io
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.nem.spectro.util;

import java.io.Serializable;


/**
 * Base class for BotMill network operations response.
 * 
 * @author Donato Rimenti
 */
public class NemSpectroNetworkResponse implements Serializable {

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
		NemSpectroNetworkResponse other = (NemSpectroNetworkResponse) obj;
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
