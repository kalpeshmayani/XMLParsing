package com.kpinfotech.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.kpinfotech.model.XmlItem;

import android.util.Log;

public class XmlParser {

	// XML node keys
	static final String KEY_ITEM = "item"; // parent node
	static final String KEY_ID = "id";
	static final String KEY_NAME = "name";
	static final String KEY_COST = "cost";
	static final String KEY_DESC = "description";

	public XmlParser() {
	}

	public ArrayList<XmlItem> parse(InputStream inputStream) {
		ArrayList<XmlItem> items = new ArrayList<XmlItem>();

		try {
			String xml = new String();

			// Read all the text returned by the server
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
			String response;
			while ((response = in.readLine()) != null) {
				xml += response;
			}
			in.close();

			NodeList nl = getDomElement(xml).getElementsByTagName(KEY_ITEM);

			for (int i = 0; i < nl.getLength(); i++) {

				Element e = (Element) nl.item(i);

				String id = getValue(e, KEY_ID);
				String name = getValue(e, KEY_NAME);
				String cost = getValue(e, KEY_COST);
				String description = getValue(e, KEY_DESC);

				XmlItem item = new XmlItem(id, name, cost, description);
				items.add(item);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return items;
	}

	public Document getDomElement(String xml) {
		Document document = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {

			DocumentBuilder db = dbf.newDocumentBuilder();

			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			document = db.parse(is);

		} catch (ParserConfigurationException e) {
			Log.e("Error: ", e.getMessage());
			return null;
		} catch (SAXException e) {
			Log.e("Error: ", e.getMessage());
			return null;
		} catch (IOException e) {
			Log.e("Error: ", e.getMessage());
			return null;
		}

		return document;
	}

	public String getValue(Element item, String str) {
		NodeList nl = item.getElementsByTagName(str);
		return this.getElementValue(nl.item(0));
	}

	public final String getElementValue(Node element) {
		Node child;
		if (element != null) {
			if (element.hasChildNodes()) {
				for (child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
					if (child.getNodeType() == Node.TEXT_NODE) {
						return child.getNodeValue();
					}
				}
			}
		}
		return "";
	}

}