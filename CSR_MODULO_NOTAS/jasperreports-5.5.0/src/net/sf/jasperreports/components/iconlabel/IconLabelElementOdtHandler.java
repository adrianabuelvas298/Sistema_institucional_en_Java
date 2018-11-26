/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports.components.iconlabel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRGenericPrintElement;
import net.sf.jasperreports.engine.JRPrintImage;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.export.JRExporterGridCell;
import net.sf.jasperreports.engine.export.oasis.GenericElementOdtHandler;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporterContext;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id:ChartThemesUtilities.java 2595 2009-02-10 17:56:51Z teodord $
 */
public class IconLabelElementOdtHandler implements GenericElementOdtHandler
{
	private static final IconLabelElementOdtHandler INSTANCE = new IconLabelElementOdtHandler();
	
	public static IconLabelElementOdtHandler getInstance()
	{
		return INSTANCE;
	}

	@Override
	public void exportElement(
		JROdtExporterContext exporterContext,
		JRGenericPrintElement element, 
		JRExporterGridCell gridCell
		) 
	{
		JRPrintText labelPrintText = (JRPrintText)element.getParameterValue(IconLabelElement.PARAMETER_LABEL_TEXT_ELEMENT);
		if (labelPrintText != null)
		{
			try
			{
				JROdtExporter exporter = (JROdtExporter)exporterContext.getExporter();
				exporter.exportText(exporterContext.getTableBuilder(), labelPrintText, gridCell);
			}
			catch (Exception e)
			{
				throw new RuntimeException(e);
			}
		}
	}

	public boolean toExport(JRGenericPrintElement element) 
	{
		return true;
	}

	@Override
	public JRPrintImage getImage(JROdtExporterContext exporterContext,
			JRGenericPrintElement element) throws JRException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
