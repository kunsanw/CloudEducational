
     /*
   * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
   *
   * Copyright 2017 摩码创想, support@momathink.com
    *
   * This file is part of Jiaowu_v1.0.
   * Jiaowu_v1.0 is free software: you can redistribute it and/or modify
   * it under the terms of the GNU Lesser General Public License as published by
   * the Free Software Foundation, either version 3 of the License, or
   * (at your option) any later version.
   *
   * Jiaowu_v1.0 is distributed in the hope that it will be useful,
   * but WITHOUT ANY WARRANTY; without even the implied warranty of
   * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   * GNU Lesser General Public License for more details.
   *
   * You should have received a copy of the GNU Lesser General Public License
   * along with Jiaowu_v1.0.  If not, see <http://www.gnu.org/licenses/>.
   *
   * 这个文件是Jiaowu_v1.0的一部分。
   * 您可以单独使用或分发这个文件，但请不要移除这个头部声明信息.
    * Jiaowu_v1.0是一个自由软件，您可以自由分发、修改其中的源代码或者重新发布它，
   * 新的任何修改后的重新发布版必须同样在遵守LGPL3或更后续的版本协议下发布.
   * 关于LGPL协议的细则请参考COPYING文件，
   * 您可以在Jiaowu_v1.0的相关目录中获得LGPL协议的副本，
   * 如果没有找到，请连接到 http://www.gnu.org/licenses/ 查看。
   *
   * - Author:摩码创想
   * - Contact: support@momathink.com
   * - License: GNU Lesser General Public License (GPL)
   */

package com.momathink.sys.dict.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import com.jfinal.kit.StrKit;


/** 掩码
 *  * <br>
 使用 方法:
 页面引人 
 <%@taglib prefix="d" uri="/WEB-INF/classes/com/momathink/sys/dict/tag/jfinal.tld"%>
 
 <:dt ...Alt + / 
 */
public class MaskTag extends TagSupport {

	
	private static final long serialVersionUID = -6219158520344634864L;
	
    private Object val;
    
    private String type;
    
    
	public Object getVal() {
		return val;
	}

	public void setVal(Object val) {
		this.val = val;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
    public int doStartTag() throws JspException {
        try {
        	String value = val + "";
        	if(!StrKit.isBlank(value) && !value.equals("null") && value.length() >= 2){
        		int length = value.length();
        		if("phone".equals(type)){
        			if(length >= 8)
        				value = value.substring(0, 3) + "****" + value.substring(7);
        		}else if("name".equals(type)){
        			value = value.substring(0, length-1) + "*";
        		}else{
        			String xing = "";
        			for (int i = 0; i < length/2; i++)
        				xing = xing + "*";
	        		value = xing + value.substring(length/2);
        		}
        	}else{ 
        		if(val==null)
        			value = "";
    		}
    		pageContext.getOut().write(value);
        } catch (IOException e) {
            return Tag.SKIP_BODY;
        }
        return Tag.EVAL_BODY_INCLUDE;
    }
 
}

