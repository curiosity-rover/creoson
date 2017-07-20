/*
 * MIT LICENSE
 * Copyright 2000-2017 Simplified Logic, Inc
 * Permission is hereby granted, free of charge, to any person obtaining a copy 
 * of this software and associated documentation files (the "Software"), to deal 
 * in the Software without restriction, including without limitation the rights 
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
 * copies of the Software, and to permit persons to whom the Software is 
 * furnished to do so, subject to the following conditions: The above copyright 
 * notice and this permission notice shall be included in all copies or 
 * substantial portions of the Software. THE SOFTWARE IS PROVIDED "AS IS", 
 * WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED 
 * TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE 
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE 
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.simplifiedlogic.nitro.jshell.json.help;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.simplifiedlogic.nitro.jlink.data.ViewDisplayData;
import com.simplifiedlogic.nitro.jshell.json.request.JLDrawingRequestParams;
import com.simplifiedlogic.nitro.jshell.json.request.JLFileRequestParams;
import com.simplifiedlogic.nitro.jshell.json.response.JLDrawingResponseParams;
import com.simplifiedlogic.nitro.jshell.json.response.JLFileResponseParams;
import com.simplifiedlogic.nitro.jshell.json.template.FunctionArgument;
import com.simplifiedlogic.nitro.jshell.json.template.FunctionExample;
import com.simplifiedlogic.nitro.jshell.json.template.FunctionObject;
import com.simplifiedlogic.nitro.jshell.json.template.FunctionReturn;
import com.simplifiedlogic.nitro.jshell.json.template.FunctionSpec;
import com.simplifiedlogic.nitro.jshell.json.template.FunctionTemplate;

/**
 * Generate help doc for "drawing" functions
 * 
 * @author Adam Andrews
 *
 */
public class JLJsonDrawingHelp extends JLJsonCommandHelp implements JLDrawingRequestParams, JLDrawingResponseParams {

	public static final String OBJ_VIEW_DISPLAY_DATA = "ViewDisplayData";
	
	/* (non-Javadoc)
	 * @see com.simplifiedlogic.nitro.jshell.json.help.JLJsonCommandHelp#getHelp()
	 */
	public List<FunctionTemplate> getHelp() {
		List<FunctionTemplate> list = new ArrayList<FunctionTemplate>();
		list.add(helpAddModel());
		list.add(helpCreate());
		list.add(helpCreateGeneralView());
		list.add(helpCreateProjectionView());
		list.add(helpDeleteModels());
		list.add(helpDeleteSheet());
		list.add(helpDeleteView());
		list.add(helpGetCurModel());
		list.add(helpGetCurSheet());
		list.add(helpGetNumSheets());
		list.add(helpGetSheetScale());
		list.add(helpGetSheetSize());
		list.add(helpGetViewLoc());
		list.add(helpGetViewScale());
		list.add(helpGetViewSheet());
		list.add(helpListModels());
		list.add(helpListViews());
		list.add(helpRegenerate());
		list.add(helpRegenerateSheet());
		list.add(helpRenameView());
		list.add(helpScaleSheet());
		list.add(helpScaleView());
		list.add(helpSelectSheet());
		list.add(helpSetCurModel());
		list.add(helpSetViewLoc());
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.simplifiedlogic.nitro.jshell.json.help.JLJsonCommandHelp#getHelpObjects()
	 */
	public List<FunctionObject> getHelpObjects() {
		List<FunctionObject> list = new ArrayList<FunctionObject>();
		list.add(helpViewDisplayData());
		return list;
	}
	
	private FunctionTemplate helpCreate() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_CREATE);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("Create a new drawing from a template");
    	FunctionArgument arg;
    	FunctionReturn ret;
    	
    	arg = new FunctionArgument(PARAM_MODEL, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Model name");
    	arg.setDefaultValue("Current active model");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("New drawing name");
    	arg.setDefaultValue("A name derived from the model's instance name");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_TEMPLATE, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Template");
    	arg.setRequired(true);
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_SCALE, FunctionSpec.TYPE_DOUBLE);
    	arg.setDescription("Drawing scale");
    	arg.setDefaultValue("1.0");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_DISPLAY, FunctionSpec.TYPE_BOOL);
    	arg.setDescription("Display the drawing after opening");
    	arg.setDefaultValue("false");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_ACTIVATE, FunctionSpec.TYPE_BOOL);
    	arg.setDescription("Activate the drawing window after opening");
    	arg.setDefaultValue("false");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_NEWWIN, FunctionSpec.TYPE_BOOL);
    	arg.setDescription("Open drawing in a new window");
    	arg.setDefaultValue("false");
    	spec.addArgument(arg);

    	ret = new FunctionReturn(OUTPUT_DRAWING, FunctionSpec.TYPE_STRING);
    	ret.setDescription("New drawing name");
    	spec.addReturn(ret);
        
    	FunctionExample ex;

    	ex = new FunctionExample();
    	ex.addInput(PARAM_MODEL, "box.prt");
    	ex.addInput(PARAM_DRAWING, "box-test.drw");
    	ex.addInput(PARAM_TEMPLATE, "main_template");
    	ex.addInput(PARAM_SCALE, 0.5);
    	ex.addInput(PARAM_DISPLAY, true);
    	ex.addInput(PARAM_ACTIVATE, true);
    	ex.addInput(PARAM_NEWWIN, true);
    	ex.addOutput(OUTPUT_DRAWING, "box-test.drw");
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_MODEL, "thick_bracket<bracket>.prt");
    	ex.addInput(PARAM_TEMPLATE, "main_template");
    	ex.addInput(PARAM_DISPLAY, true);
    	ex.addInput(PARAM_ACTIVATE, true);
    	ex.addOutput(OUTPUT_DRAWING, "thick_bracket.drw");
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_TEMPLATE, "main_template");
    	ex.addInput(PARAM_SCALE, 0.5);
    	ex.addOutput(OUTPUT_DRAWING, "box.drw");
    	template.addExample(ex);
    	
        return template;
    }
    
	private FunctionTemplate helpListModels() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_LIST_MODELS);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("List the models contained in a drawing");
    	FunctionArgument arg;
    	FunctionReturn ret;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_MODEL, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Model name filter");
    	arg.setWildcards(true);
    	arg.setDefaultValue("no filter");
    	spec.addArgument(arg);

    	ret = new FunctionReturn(OUTPUT_MODELS, FunctionSpec.TYPE_ARRAY, FunctionSpec.TYPE_STRING);
    	ret.setDescription("List of model names in the drawing");
    	spec.addReturn(ret);

    	FunctionExample ex;
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addOutput(OUTPUT_MODELS, new String[] {"box.prt","screw.prt"});
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_MODEL, "box.prt");
    	ex.addOutput(OUTPUT_MODELS, new String[] {"box.prt"});
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_MODEL, "*w*");
    	ex.addOutput(OUTPUT_MODELS, new String[] {"screw.prt"});
    	template.addExample(ex);
    	
    	return template;
	}

	private FunctionTemplate helpAddModel() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_ADD_MODEL);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("Add a model to a drawing");
    	FunctionArgument arg;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_MODEL, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Model name");
    	arg.setRequired(true);
    	spec.addArgument(arg);

    	FunctionExample ex;
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_MODEL, "screw.prt");
    	template.addExample(ex);
    	
    	return template;
	}

	private FunctionTemplate helpDeleteModels() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_DELETE_MODELS);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("Delete one or more models from a drawing");
    	FunctionArgument arg;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_MODEL, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Model name");
    	arg.setWildcards(true);
    	arg.setDefaultValue("All models will be deleted from the drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_DELETE_VIEWS, FunctionSpec.TYPE_BOOL);
    	arg.setDescription("Whether to delete drawing views associated with the model");
    	arg.setDefaultValue("false");
    	spec.addArgument(arg);

    	FunctionExample ex;
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_MODEL, "screw.prt");
    	ex.addInput(PARAM_DELETE_VIEWS, true);
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_MODEL, "*w*");
    	template.addExample(ex);
    	
    	return template;
	}

	private FunctionTemplate helpGetCurModel() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_GET_CUR_MODEL);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("Get the active model on a drawing");
    	FunctionArgument arg;
    	FunctionReturn ret;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	ret = new FunctionReturn(OUTPUT_MODEL, FunctionSpec.TYPE_STRING);
    	ret.setDescription("Model name");
    	spec.addReturn(ret);
        
    	FunctionExample ex;

    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addOutput(OUTPUT_DRAWING, "box.prt");
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addOutput(OUTPUT_DRAWING, "screw.prt");
    	template.addExample(ex);
    	
        return template;
    }
    
	private FunctionTemplate helpSetCurModel() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_SET_CUR_MODEL);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("Set the active model on a drawing");
    	FunctionArgument arg;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_MODEL, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Model name");
    	arg.setRequired(true);
    	spec.addArgument(arg);

    	FunctionExample ex;
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_MODEL, "screw.prt");
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_MODEL, "box.prt");
    	template.addExample(ex);
    	
    	return template;
	}

	private FunctionTemplate helpRegenerate() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_REGENERATE);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("Regenerate a drawing");
    	FunctionArgument arg;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	FunctionExample ex;

    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	template.addExample(ex);
    	
        return template;
    }
    
	private FunctionTemplate helpRegenerateSheet() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_REGENERATE_SHEET);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("Regenerate a sheet on a drawing");
    	FunctionArgument arg;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_SHEET, FunctionSpec.TYPE_INTEGER);
    	arg.setDescription("Sheet number (0 for all sheets)");
    	arg.setDefaultValue("All sheets will be regenerated");
    	spec.addArgument(arg);

    	FunctionExample ex;

    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_SHEET, 1);
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_SHEET, 2);
    	template.addExample(ex);
    	
        return template;
    }
    
	private FunctionTemplate helpSelectSheet() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_SELECT_SHEET);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("Make a drawing sheet the current sheet");
    	FunctionArgument arg;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_SHEET, FunctionSpec.TYPE_INTEGER);
    	arg.setDescription("Sheet number");
    	arg.setRequired(true);
    	spec.addArgument(arg);

    	FunctionExample ex;

    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_SHEET, 1);
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_SHEET, 2);
    	template.addExample(ex);
    	
        return template;
    }
    
	private FunctionTemplate helpDeleteSheet() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_DELETE_SHEET);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("Delete a drawing sheet");
    	spec.addFootnote("An error will occur if you try to delete the only sheet in a drawing.");
    	FunctionArgument arg;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_SHEET, FunctionSpec.TYPE_INTEGER);
    	arg.setDescription("Sheet number");
    	arg.setRequired(true);
    	spec.addArgument(arg);

    	FunctionExample ex;

    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_SHEET, 1);
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_SHEET, 2);
    	template.addExample(ex);
    	
        return template;
    }
    
	private FunctionTemplate helpGetCurSheet() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_GET_CUR_SHEET);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("Get the current drawing sheet");
    	FunctionArgument arg;
    	FunctionReturn ret;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	ret = new FunctionReturn(OUTPUT_SHEET, FunctionSpec.TYPE_INTEGER);
    	ret.setDescription("Sheet number");
    	spec.addReturn(ret);
        
    	FunctionExample ex;

    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addOutput(OUTPUT_SHEET, 1);
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addOutput(OUTPUT_SHEET, 2);
    	template.addExample(ex);
    	
        return template;
    }
    
	private FunctionTemplate helpGetNumSheets() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_GET_NUM_SHEETS);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("Get the number of sheets on a drawing");
    	FunctionArgument arg;
    	FunctionReturn ret;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	ret = new FunctionReturn(OUTPUT_NUM_SHEETS, FunctionSpec.TYPE_INTEGER);
    	ret.setDescription("Number of sheets");
    	spec.addReturn(ret);
        
    	FunctionExample ex;

    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addOutput(OUTPUT_NUM_SHEETS, 2);
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addOutput(OUTPUT_NUM_SHEETS, 3);
    	template.addExample(ex);
    	
        return template;
    }
    
	private FunctionTemplate helpScaleSheet() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_SCALE_SHEET);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("Set the scale of a drawing sheet");
    	FunctionArgument arg;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_SHEET, FunctionSpec.TYPE_INTEGER);
    	arg.setDescription("Sheet Number");
    	arg.setRequired(true);
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_SCALE, FunctionSpec.TYPE_DOUBLE);
    	arg.setDescription("View scale");
    	arg.setRequired(true);
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_MODEL, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing model to scale");
    	arg.setDefaultValue("The active model on the drawing");
    	spec.addArgument(arg);

    	FunctionExample ex;

    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_SHEET, 1);
    	ex.addInput(PARAM_SCALE, 0.5);
    	ex.addInput(PARAM_MODEL, "box.prt");
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_SHEET, 2);
    	ex.addInput(PARAM_SCALE, 1.0);
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_SHEET, 1);
    	ex.addInput(PARAM_SCALE, 0.5);
    	template.addExample(ex);
    	
        return template;
    }
    
	private FunctionTemplate helpGetSheetScale() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_GET_SHEET_SCALE);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("Get the scale of a drawing sheet");
    	FunctionArgument arg;
    	FunctionReturn ret;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_SHEET, FunctionSpec.TYPE_INTEGER);
    	arg.setDescription("Sheet number");
    	arg.setRequired(true);
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_MODEL, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing model used to calculate the scale");
    	arg.setDefaultValue("The active model on the drawing");
    	spec.addArgument(arg);

    	ret = new FunctionReturn(OUTPUT_SCALE, FunctionSpec.TYPE_DOUBLE);
    	ret.setDescription("Sheet scale");
    	spec.addReturn(ret);
        
    	FunctionExample ex;

    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_SHEET, 1);
    	ex.addInput(PARAM_MODEL, "box.prt");
    	ex.addOutput(OUTPUT_SCALE, 0.5);
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_SHEET, 2);
    	ex.addOutput(OUTPUT_SCALE, 1.0);
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_SHEET, 1);
    	ex.addOutput(OUTPUT_SCALE, 0.5);
    	template.addExample(ex);
    	
        return template;
    }
    
	private FunctionTemplate helpGetSheetSize() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_GET_SHEET_SIZE);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("Get the size of a drawing sheet");
    	FunctionArgument arg;
    	FunctionReturn ret;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_SHEET, FunctionSpec.TYPE_INTEGER);
    	arg.setDescription("Sheet number");
    	arg.setRequired(true);
    	spec.addArgument(arg);

    	ret = new FunctionReturn(OUTPUT_SIZE, FunctionSpec.TYPE_STRING);
    	ret.setDescription("Sheet size");
    	spec.addReturn(ret);
        
    	FunctionExample ex;

    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_SHEET, 1);
    	ex.addOutput(OUTPUT_SIZE, "A4");
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_SHEET, 1);
    	ex.addOutput(OUTPUT_SIZE, "F");
    	template.addExample(ex);
    	
        return template;
    }
    
	private FunctionTemplate helpCreateGeneralView() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_CREATE_GEN_VIEW);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("Create general view on a drawing");
    	FunctionArgument arg;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_VIEW, FunctionSpec.TYPE_STRING);
    	arg.setDescription("New view name");
    	arg.setDefaultValue("The " + PARAM_MODEL_VIEW + " parameter");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_SHEET, FunctionSpec.TYPE_INTEGER);
    	arg.setDescription("Sheet number");
    	arg.setDefaultValue("Current active sheet on the drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_MODEL, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Model for the view");
    	arg.setDefaultValue("Current active model on the drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_MODEL_VIEW, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Model view to use for the drawing view orientation");
    	arg.setRequired(true);
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_POINT, FunctionSpec.TYPE_OBJECT, JLJsonFileHelp.OBJ_POINT);
    	arg.setDescription("Coordinates for the view");
    	arg.setRequired(true);
    	spec.addArgument(arg);
        
    	arg = new FunctionArgument(PARAM_SCALE, FunctionSpec.TYPE_DOUBLE);
    	arg.setDescription("View scale");
    	arg.setDefaultValue("The sheet's scale");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_DISPLAY_DATA, FunctionSpec.TYPE_OBJECT, OBJ_VIEW_DISPLAY_DATA);
    	arg.setDescription("Display parameters used to create the view");
    	arg.setDefaultValue("Creo defaults");
    	spec.addArgument(arg);
        
    	FunctionExample ex;
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_VIEW, "RIGHT_TEST");
    	ex.addInput(PARAM_SHEET, 1);
    	ex.addInput(PARAM_MODEL, "box.prt");
    	ex.addInput(PARAM_MODEL_VIEW, "RIGHT");
    	Map<String, Object> rec;
    	rec = new OrderedMap<String, Object>();
    	rec.put(JLFileRequestParams.PARAM_X, 10.0);
    	rec.put(JLFileRequestParams.PARAM_Y, 3.25);
    	ex.addInput(PARAM_POINT, rec);
    	ex.addInput(PARAM_SCALE, 0.25);
    	rec = new OrderedMap<String, Object>();
    	rec.put(PARAM_CABLE_STYLE, ViewDisplayData.CABLESTYLE_DEFAULT);
    	rec.put(PARAM_STYLE, ViewDisplayData.STYLE_HIDDEN_LINE);
    	rec.put(PARAM_TANGENT_STYLE, ViewDisplayData.TANGENT_NONE);
    	rec.put(PARAM_REMOVE_QUILT_HIDDEN_LINES, true);
    	rec.put(PARAM_SHOW_CONCEPT_MODEL, false);
    	rec.put(PARAM_SHOW_WELD_XSECTION, false);
    	ex.addInput(PARAM_DISPLAY_DATA, rec);
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_MODEL_VIEW, "RIGHT");
    	rec = new OrderedMap<String, Object>();
    	rec.put(JLFileRequestParams.PARAM_X, 10.0);
    	rec.put(JLFileRequestParams.PARAM_Y, 3.25);
    	ex.addInput(PARAM_POINT, rec);
    	rec = new OrderedMap<String, Object>();
    	rec.put(PARAM_STYLE, ViewDisplayData.STYLE_HIDDEN_LINE);
    	rec.put(PARAM_TANGENT_STYLE, ViewDisplayData.TANGENT_NONE);
    	ex.addInput(PARAM_DISPLAY_DATA, rec);
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_MODEL_VIEW, "RIGHT");
    	rec = new OrderedMap<String, Object>();
    	rec.put(JLFileRequestParams.PARAM_X, 10.0);
    	rec.put(JLFileRequestParams.PARAM_Y, 3.25);
    	ex.addInput(PARAM_POINT, rec);
    	template.addExample(ex);
    	
    	return template;
	}

	private FunctionTemplate helpCreateProjectionView() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_CREATE_PROJ_VIEW);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("Create projection view on a drawing");
    	spec.addFootnote("When specifying the view coordinates, you should specify only an X or a Y coordinate to avoid confusion.  If you specify both coordinates, it appears Creo may be using whichever has the larger absolute value.");
    	FunctionArgument arg;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_VIEW, FunctionSpec.TYPE_STRING);
    	arg.setDescription("New view name");
    	arg.setDefaultValue("Creo's default name for a new view");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_SHEET, FunctionSpec.TYPE_INTEGER);
    	arg.setDescription("Sheet number");
    	arg.setDefaultValue("Current active sheet on the drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_PARENT_VIEW, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Parent view for the projection view");
    	arg.setRequired(true);
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_POINT, FunctionSpec.TYPE_OBJECT, JLJsonFileHelp.OBJ_POINT);
    	arg.setDescription("Coordinates for the view, relative to the location of the parent view");
    	arg.setRequired(true);
    	spec.addArgument(arg);
        
    	arg = new FunctionArgument(PARAM_DISPLAY_DATA, FunctionSpec.TYPE_OBJECT, OBJ_VIEW_DISPLAY_DATA);
    	arg.setDescription("Display parameters used to create the view");
    	arg.setDefaultValue("The display parameters of the parent view");
    	spec.addArgument(arg);
        
    	FunctionExample ex;
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_VIEW, "RIGHT_TEST");
    	ex.addInput(PARAM_SHEET, 1);
    	ex.addInput(PARAM_PARENT_VIEW, "FRONT");
    	Map<String, Object> rec;
    	rec = new OrderedMap<String, Object>();
    	rec.put(JLFileRequestParams.PARAM_X, 10.0);
    	ex.addInput(PARAM_POINT, rec);
    	ex.addInput(PARAM_SCALE, 0.25);
    	rec = new OrderedMap<String, Object>();
    	rec.put(PARAM_CABLE_STYLE, ViewDisplayData.CABLESTYLE_DEFAULT);
    	rec.put(PARAM_STYLE, ViewDisplayData.STYLE_HIDDEN_LINE);
    	rec.put(PARAM_TANGENT_STYLE, ViewDisplayData.TANGENT_NONE);
    	rec.put(PARAM_REMOVE_QUILT_HIDDEN_LINES, true);
    	rec.put(PARAM_SHOW_CONCEPT_MODEL, false);
    	rec.put(PARAM_SHOW_WELD_XSECTION, false);
    	ex.addInput(PARAM_DISPLAY_DATA, rec);
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_PARENT_VIEW, "FRONT");
    	rec = new OrderedMap<String, Object>();
    	rec.put(JLFileRequestParams.PARAM_X, 10.0);
    	ex.addInput(PARAM_POINT, rec);
    	rec = new OrderedMap<String, Object>();
    	rec.put(PARAM_STYLE, ViewDisplayData.STYLE_HIDDEN_LINE);
    	rec.put(PARAM_TANGENT_STYLE, ViewDisplayData.TANGENT_NONE);
    	ex.addInput(PARAM_DISPLAY_DATA, rec);
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_VIEW, "TOP");
    	ex.addInput(PARAM_PARENT_VIEW, "FRONT");
    	rec = new OrderedMap<String, Object>();
    	rec.put(JLFileRequestParams.PARAM_Y, 3.25);
    	ex.addInput(PARAM_POINT, rec);
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_PARENT_VIEW, "LEFT");
    	rec = new OrderedMap<String, Object>();
    	rec.put(JLFileRequestParams.PARAM_Y, 3.25);
    	ex.addInput(PARAM_POINT, rec);
    	ex.createError("Parent view does not exist in drawing: LEFT");
    	template.addExample(ex);
    	
    	return template;
	}

	private FunctionTemplate helpListViews() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_LIST_VIEWS);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("List the views contained in a drawing");
    	FunctionArgument arg;
    	FunctionReturn ret;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_VIEW, FunctionSpec.TYPE_STRING);
    	arg.setDescription("View name filter");
    	arg.setWildcards(true);
    	arg.setDefaultValue("no filter");
    	spec.addArgument(arg);

    	ret = new FunctionReturn(OUTPUT_VIEWS, FunctionSpec.TYPE_ARRAY, FunctionSpec.TYPE_STRING);
    	ret.setDescription("List of views in the drawing");
    	spec.addReturn(ret);

    	FunctionExample ex;
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addOutput(OUTPUT_VIEWS, new String[] {"FRONT","RIGHT","TOP"});
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_MODEL, "RIGHT");
    	ex.addOutput(OUTPUT_VIEWS, new String[] {"RIGHT"});
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_MODEL, "*o*");
    	ex.addOutput(OUTPUT_VIEWS, new String[] {"FRONT","TOP"});
    	template.addExample(ex);
    	
    	return template;
	}

	private FunctionTemplate helpGetViewLoc() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_GET_VIEW_LOC);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("Get the location of a drawing view");
    	FunctionArgument arg;
    	FunctionReturn ret;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_VIEW, FunctionSpec.TYPE_STRING);
    	arg.setDescription("View name");
    	arg.setRequired(true);
    	spec.addArgument(arg);

    	ret = new FunctionReturn(JLFileResponseParams.OUTPUT_X, FunctionSpec.TYPE_DOUBLE);
    	ret.setDescription("X-coordinate of the view");
    	spec.addReturn(ret);
        
    	ret = new FunctionReturn(JLFileResponseParams.OUTPUT_Y, FunctionSpec.TYPE_DOUBLE);
    	ret.setDescription("Y-coordinate of the view");
    	spec.addReturn(ret);
        
    	ret = new FunctionReturn(JLFileResponseParams.OUTPUT_Z, FunctionSpec.TYPE_DOUBLE);
    	ret.setDescription("Z-coordinate of the view");
    	spec.addReturn(ret);
        
    	FunctionExample ex;

    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_VIEW, "FRONT");
    	ex.addOutput(JLFileResponseParams.OUTPUT_X, 2.5);
    	ex.addOutput(JLFileResponseParams.OUTPUT_Y, 4.0);
    	ex.addOutput(JLFileResponseParams.OUTPUT_Z, 0.5);
    	template.addExample(ex);
    	
        return template;
    }
    
	private FunctionTemplate helpSetViewLoc() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_SET_VIEW_LOC);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("Set the location of a drawing view");
    	FunctionArgument arg;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_VIEW, FunctionSpec.TYPE_STRING);
    	arg.setDescription("View name");
    	arg.setRequired(true);
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_POINT, FunctionSpec.TYPE_OBJECT, JLJsonFileHelp.OBJ_POINT);
    	arg.setDescription("Coordinates for the view");
    	arg.setRequired(true);
    	spec.addArgument(arg);
        
    	FunctionExample ex;

    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_VIEW, "FRONT");
    	Map<String, Object> rec;
    	rec = new OrderedMap<String, Object>();
    	rec.put(JLFileRequestParams.PARAM_X, 2.5);
    	rec.put(JLFileRequestParams.PARAM_Y, 4.0);
    	rec.put(JLFileRequestParams.PARAM_Z, 0.5);
    	ex.addInput(PARAM_POINT, rec);
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_VIEW, "RIGHT");
    	rec = new OrderedMap<String, Object>();
    	rec.put(JLFileRequestParams.PARAM_X, 13.0);
    	rec.put(JLFileRequestParams.PARAM_Y, 4.0);
    	ex.addInput(PARAM_POINT, rec);
    	template.addExample(ex);
    	
        return template;
    }
    
	private FunctionTemplate helpDeleteView() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_DELETE_VIEW);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("Delete a drawing view");
    	FunctionArgument arg;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_VIEW, FunctionSpec.TYPE_STRING);
    	arg.setDescription("View name");
    	arg.setRequired(true);
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_SHEET, FunctionSpec.TYPE_INTEGER);
    	arg.setDescription("Sheet number; if filled in, the view will only be deleted if it is on that sheet");
    	arg.setDefaultValue("Delete the view from any sheet");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_DEL_CHILDREN, FunctionSpec.TYPE_BOOL);
    	arg.setDescription("Whether to also delete any children of the view");
    	arg.setDefaultValue("false");
    	spec.addArgument(arg);

    	FunctionExample ex;

    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_VIEW, "FRONT");
    	ex.addInput(PARAM_SHEET, 1);
    	ex.addInput(PARAM_DEL_CHILDREN, true);
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_VIEW, "RIGHT");
    	template.addExample(ex);
    	
        return template;
    }
    
	private FunctionTemplate helpRenameView() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_RENAME_VIEW);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("Rename a drawing view");
    	FunctionArgument arg;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_VIEW, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Old view name");
    	arg.setRequired(true);
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_NEWVIEW, FunctionSpec.TYPE_STRING);
    	arg.setDescription("New view name");
    	arg.setRequired(true);
    	spec.addArgument(arg);

    	FunctionExample ex;

    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_VIEW, "FRONT");
    	ex.addInput(PARAM_NEWVIEW, "FRONT_test");
    	template.addExample(ex);
    	
        return template;
    }
    
	private FunctionTemplate helpScaleView() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_SCALE_VIEW);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("Set the scale of one or more drawing views");
    	FunctionArgument arg;
    	FunctionReturn ret;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_VIEW, FunctionSpec.TYPE_STRING);
    	arg.setDescription("View name");
    	arg.setWildcards(true);
    	arg.setDefaultValue("All views will be scaled");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_SCALE, FunctionSpec.TYPE_DOUBLE);
    	arg.setDescription("View scale");
    	arg.setRequired(true);
    	spec.addArgument(arg);

    	ret = new FunctionReturn(OUTPUT_SUCCESS_VIEWS, FunctionSpec.TYPE_ARRAY, FunctionSpec.TYPE_STRING);
    	ret.setDescription("List of view which were successfully scaled");
    	spec.addReturn(ret);
        
    	ret = new FunctionReturn(OUTPUT_FAILED_VIEWS, FunctionSpec.TYPE_ARRAY, FunctionSpec.TYPE_STRING);
    	ret.setDescription("List of view which failed to scale");
    	spec.addReturn(ret);
        
    	FunctionExample ex;

    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_VIEW, "FRONT");
    	ex.addInput(PARAM_SCALE, 0.5);
    	ex.addOutput(OUTPUT_SUCCESS_VIEWS, new String[] {"FRONT"});
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_SCALE, 0.5);
    	ex.addOutput(OUTPUT_SUCCESS_VIEWS, new String[] {"FRONT"});
    	ex.addOutput(OUTPUT_FAILED_VIEWS, new String[] {"RIGHT","TOP"});
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_VIEW, "*O*");
    	ex.addInput(PARAM_SCALE, 0.5);
    	ex.addOutput(OUTPUT_SUCCESS_VIEWS, new String[] {"FRONT"});
    	ex.addOutput(OUTPUT_FAILED_VIEWS, new String[] {"TOP"});
    	template.addExample(ex);
    	
        return template;
    }
    
	private FunctionTemplate helpGetViewScale() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_GET_VIEW_SCALE);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("Get the scale of a drawing view");
    	FunctionArgument arg;
    	FunctionReturn ret;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_VIEW, FunctionSpec.TYPE_STRING);
    	arg.setDescription("View name");
    	arg.setRequired(true);
    	spec.addArgument(arg);

    	ret = new FunctionReturn(OUTPUT_SCALE, FunctionSpec.TYPE_DOUBLE);
    	ret.setDescription("View scale");
    	spec.addReturn(ret);
        
    	FunctionExample ex;

    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_VIEW, "FRONT");
    	ex.addOutput(OUTPUT_SCALE, 0.5);
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_VIEW, "RIGHT");
    	ex.addOutput(OUTPUT_SCALE, 1.0);
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_VIEW, "FRONT");
    	ex.addOutput(OUTPUT_SCALE, 0.5);
    	template.addExample(ex);
    	
        return template;
    }
    
	private FunctionTemplate helpGetViewSheet() {
    	FunctionTemplate template = new FunctionTemplate(COMMAND, FUNC_GET_VIEW_SHEET);
    	FunctionSpec spec = template.getSpec();
    	spec.setFunctionDescription("Get the sheet number that contains a drawing view");
    	FunctionArgument arg;
    	FunctionReturn ret;
    	
    	arg = new FunctionArgument(PARAM_DRAWING, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Drawing name");
    	arg.setDefaultValue("Current active drawing");
    	spec.addArgument(arg);

    	arg = new FunctionArgument(PARAM_VIEW, FunctionSpec.TYPE_STRING);
    	arg.setDescription("View name");
    	arg.setRequired(true);
    	spec.addArgument(arg);

    	ret = new FunctionReturn(OUTPUT_SHEET, FunctionSpec.TYPE_INTEGER);
    	ret.setDescription("Sheet number");
    	spec.addReturn(ret);
        
    	FunctionExample ex;

    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_VIEW, "FRONT");
    	ex.addOutput(OUTPUT_SHEET, 1);
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_DRAWING, "box.drw");
    	ex.addInput(PARAM_VIEW, "RIGHT");
    	ex.addOutput(OUTPUT_SHEET, 2);
    	template.addExample(ex);
    	
    	ex = new FunctionExample();
    	ex.addInput(PARAM_VIEW, "FRONT");
    	ex.addOutput(OUTPUT_SHEET, 1);
    	template.addExample(ex);
    	
        return template;
    }
    
	private FunctionObject helpViewDisplayData() {
    	FunctionObject obj = new FunctionObject(OBJ_VIEW_DISPLAY_DATA);
    	obj.setDescription("Creo display parameters used to create drawing views");

    	FunctionArgument arg;
    	arg = new FunctionArgument(PARAM_CABLE_STYLE, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Cable Style");
    	arg.setValidValues(new String[] {
        		ViewDisplayData.CABLESTYLE_DEFAULT,
        		ViewDisplayData.CABLESTYLE_CENTERLINE,
        		ViewDisplayData.CABLESTYLE_THICK
    	});
    	obj.add(arg);

    	arg = new FunctionArgument(PARAM_REMOVE_QUILT_HIDDEN_LINES, FunctionSpec.TYPE_BOOL);
    	arg.setDescription("Whether to remove quilt hidden lines");
    	obj.add(arg);

    	arg = new FunctionArgument(PARAM_SHOW_CONCEPT_MODEL, FunctionSpec.TYPE_BOOL);
    	arg.setDescription("Whether to show the concept model");
    	obj.add(arg);

    	arg = new FunctionArgument(PARAM_SHOW_WELD_XSECTION, FunctionSpec.TYPE_BOOL);
    	arg.setDescription("Whether to show weld cross-section");
    	obj.add(arg);

    	arg = new FunctionArgument(PARAM_STYLE, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Style");
    	arg.setValidValues(new String[] {
        		ViewDisplayData.STYLE_DEFAULT,
        		ViewDisplayData.STYLE_FOLLOW_ENV,
        		ViewDisplayData.STYLE_HIDDEN_LINE,
        		ViewDisplayData.STYLE_NO_HIDDEN,
        		ViewDisplayData.STYLE_SHADED,
        		ViewDisplayData.STYLE_WIREFRAME
    	});
    	obj.add(arg);

    	arg = new FunctionArgument(PARAM_TANGENT_STYLE, FunctionSpec.TYPE_STRING);
    	arg.setDescription("Tangent Style");
    	arg.setValidValues(new String[] {
        		ViewDisplayData.TANGENT_DEFAULT,
        		ViewDisplayData.TANGENT_CENTERLINE,
        		ViewDisplayData.TANGENT_DIMMED,
        		ViewDisplayData.TANGENT_NONE,
        		ViewDisplayData.TANGENT_PHANTOM,
        		ViewDisplayData.TANGENT_SOLID
    	});
    	obj.add(arg);

        return obj;
    }
    
}
