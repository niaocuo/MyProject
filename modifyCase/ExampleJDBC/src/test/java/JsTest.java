
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;


import org.mozilla.javascript.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by 顺 on 2018/8/21.
 */
public class JsTest {
    @Test
    public void test() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        for (ScriptEngineFactory f : manager.getEngineFactories()) {

            printBasicInfo(f);

            System.out.println();

        }



        ScriptEngine nashorn = manager.getEngineByName("nashorn");

        if(nashorn != null) {
            Object obj = nashorn.eval("g=[1, 2, 3];");

            System.out.println(obj.getClass().getName());

            nashorn.put("","");

            System.out.println("Nashorn is present.");

        }

        else {
            ScriptEngine rhino = manager.getEngineByName("rhino");
            System.out.println("Nashorn is not present.");

        }
    }

    @Test
    public  void test1() {
        Context ctx=Context.enter();
        Scriptable scope=ctx.initStandardObjects();
        String jsStr="a={g:[1, 2, 3]}";
//        String jsStr="var a ={g:[1, 2, 3]};";
        Object jsObject =  ctx.evaluateString(scope, jsStr, null, 0, null);
        scope.put("a",scope,jsObject);
//        ctx.evaluateString(scope, jsStr, null, 1, null);

         jsObject =  ctx.evaluateString(scope, "a.g", null, 1, null);
        Object ob = JSON.toJSONString(jsObject);
        System.out.println("result="+ob);
    }



    public static void printBasicInfo(ScriptEngineFactory factory) {

        System.out.println("engine name=" + factory.getEngineName());

        System.out.println("engine version=" + factory.getEngineVersion());

        System.out.println("language name=" + factory.getLanguageName());

        System.out.println("extensions=" + factory.getExtensions());

        System.out.println("language version=" + factory.getLanguageVersion());

        System.out.println("names=" + factory.getNames());

        System.out.println("mime types=" + factory.getMimeTypes());

    }
}
