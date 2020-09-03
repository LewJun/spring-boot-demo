package com.example.lewjun

import com.example.lewjun.Emp as EmpAlias
import com.example.lewjun.util.GsonUtil
import com.google.gson.reflect.TypeToken

import javax.script.Bindings
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.SimpleBindings
import java.time.LocalDate
import java.util.concurrent.TimeUnit
import java.util.function.Consumer
import java.util.function.Predicate

class AppGroovyTest extends GroovyTestCase {
    void testHelloWorld() {
        println "hello world"
    }

    /**
     * 变量的声明和使用
     */
    void testVar() {
        def empno = 7399
        def ename = 'King'
        def job = "Master"
        def mgr = 999
        def hireDate = LocalDate.now()
        def sex = true
        def height = 1.72f
        def weight = 66.66 // 对于小数，默认是BigDecimal类型
        println weight.getClass()
        assert sex instanceof Boolean
    }

    /**
     * groovy中 == 就是equals
     */
    void testEq() {
        // groovy中 == 就是equals
        assert "King" == "King"
        assert "King".equals("King")
    }

    /**
     * 操作符的使用
     */
    void testOpr() {
        def x = 3
        def y = 4
        def z = 5

        println x + y
        println x - y
        println x * y
        println x * y / z
        println x * y % z
        println x**y // 3的4次方

        println x++
        println x

        println(++y)
        println y

        def words = ['hello', 'worlds']
        println words
        // *.操作符
        println words*.toUpperCase()

        println "double" * 2

        // “==”操作符表示两个数的值等价（“==”号调用了对象的 equals 进行比较），而不是判断引用是否相等
        println "abc" == "abc"
        println "123" == 123

        // as
        def xStr = x as String
        println(xStr.class) // 将x转换为String
    }

    void testString() {
        def greeting = "hello world"
        def n = 5
        println("${greeting * n} $greeting")

        println(greeting[2..2]) // 获取下标为2的元素
        println(greeting[2])

        println(greeting.class) // java.lang.String

        println("${greeting * n} $greeting".class) // org.codehaus.groovy.runtime.GStringImpl

//        greeting <<= "Hi" // 同greeting <<= "Hi"
        greeting += "Hi" // hello worldHi
        println(greeting)

        greeting = "你好，世界。" // 重新复制
        println(greeting)

        println(greeting[0]) // 根据下标0得到`第一个`元素           => 你
        println(greeting[-1]) // 根据下标-1得到`最后`一个元素       => 。
        println(greeting[1..3]) // 根据下标1..3区间的内容          => 好，世

        if (greeting.contains("你好")) {
            println("包含`你好`")
        }

        println greeting.reverse()
        println greeting.length()
        println greeting.size()

        println greeting.isNumber()
        println "1".isNumber()

        def threeQuota = """{
    "id": 1,
    "name": "zs",
    "age": 23
}
"""
        println threeQuota
    }

    /**
     * 区间范围
     */
    void testRanges() {
        def nums = 5..20
        println("nums 起始 ${nums.from}")
        println("nums 结束 ${nums.to}")

        nums.each { println(it) }

        // 20次
        20.times {
            println("20.times $it")
        }

        (5..20).eachWithIndex { int entry, int index -> println("$index $entry") } // 要打括号，是(5..20)，不是5..20
        println((5..20).containsAll([10, 15]))

        println("偶数 " + nums.findAll { it % 2 == 0 })

        // 不包含c
        ('a'..<'c').reverse().each {
            println(it)
        }

        def target = 13
        switch (target) {
            case 2:
                println(2)
                break
            case 10..18:
                println(10..18)
                break
            default:
                println("不能找到")
                break
        }
    }

    /**
     * 集合
     */
    void testList() {
        def emptyList = [] // 声明一个空集合
        println(emptyList.class) // 默认是java.util.ArrayList

        def nums = [1, 7, 4, 33, 9] as LinkedList // 转换为LinkedList
        println(nums.class) // java.util.LinkedList

        assert (5..20).toList() // 将区间转换为list List<Integer>

        // 遍历
        nums.each { println(it) }

        nums[2] = 22 // 修改下标2对应的值

        println(nums[2]) // 获取下标2对应的值

        println(nums[0]) // 第一个元素
        println(nums.first)

        println(nums[-1]) // 最后一个元素
        println(nums.last)

        nums.add(11)

        nums.addAll(Arrays.asList(44, 55, 66))

        nums.removeIf(new Predicate<Integer>() {
            @Override
            boolean test(Integer integer) {
                return integer == 44
            }
        })

        println(nums.sort())

        println(nums.pop())

        def odd = nums.findAll { it % 2 == 1 }
        println("odd $odd")

        nums = nums.collect { it * 2 }
        println(nums)

        println(nums.join('-'))

        println(nums.every { it % 5 == 1 })

        println(nums.any { it % 2 == 0 })

        def fruits = ['orange', 'apple'].asImmutable() // 声明为不可变集合
        // fruits[0] = 'banana' // 修改失败，抛异常。
        println(fruits.class)

        // countries 可以用于并发访问
        def countries = ['China', 'Japan', "India"].asSynchronized()
    }

    /**
     * map
     */
    void testMaps() {
        def emptyMap = [:] // 声明空map
        println(emptyMap.getClass()) // java.util.LinkedHashMap

        def map1 = [
                'c': 3,
                'a': 1,
                'b': 2
        ]

        map1['a'] = 111 // 修改元素


        println(map1)
        println(map1.keySet())
        println(map1.values())

        println(map1['a']) // 获取键a的值

        println(
                map1.collect { key, value -> value * 2 }
        )

        // 遍历
        map1.each { println("key: ${it.key}, value: ${it.value}") }
        map1.each { key, value -> println("key: $key, value: $value") }

        def subMap = map1.subMap(Arrays.asList('a'))
        println(subMap)

        // 排序
        def sortedMap1 = map1.sort {
            a, b -> a.value <=> b.value // 从小到大排列 // b.value <=> a.value 从大到小排列 [a:111, c:3, b:2]
        }
        println(sortedMap1)


    }

    void testControl() {
        def $null = null // false
        def $blankStr = '' // false
        def $spaceStr = ' ' // true
        def $blankList = [] // false
        def $blankMap = [:] // false
        def $num0 = 0 // false
        def $num1 = 1 // true

        if (!$null) println("\$null !${$null}")

        if (!$blankStr) println("\$blankStr !${$blankStr}")

        if ($spaceStr) println("\$spaceStr ${$spaceStr}")

        if (!$blankList) println("\$blankList !${$blankList}")

        if (!$blankMap) println("\$blankMap !${$blankMap}")

        if (!$num0) println("\$num0 !${$num0}")

        if ($num1) println("\$num1 ${$num1}")

        (0.1..1.0).each { println(it) }

        'abc'.each { println(it) }

        def list = [1, 2, 3, 4, 5, 3, 0]
        list.each { println(it) }

        for (x in 'a'..'d') println(x)

        for (obj in new Emp(ename: 'King', empno: 72)) println(obj)

        // 如果list不为空，就移除第一个元素
        while (list) {
            list.pop()
        }

        println "list $list"

        def target = 18

        switch (target) {
            case 16..<28:
                println("符合区间")
                break

            case Integer:
                println("是Integer类型")
                break

            case [16, 18, 20]:
                println("符合集合")
                break

            case ~/\d/:
                println "符合正则表达式"
                break

            default:
                println("其它情况")
                break
        }

        Emp emp = null

        println(emp?.ename ?: 'default value')

        println(emp ?: new Emp(ename: 'King'))

        Optional.ofNullable(emp).ifPresent(new Consumer<Emp>() {
            @Override
            void accept(Emp e) {
                println e
            }
        })
    }

    void testFile() {
        def file = new File("d:/1.txt")
        if (file.exists()) {
            println file.text
        } else {
            if (file.createNewFile()) {
                file.text += """
{
    "empno": 123,
    "ename": 'zs'
}
"""
            }
        }
        println file.bytes.size()

        file.renameTo("d:/123.txt")
    }

    void testClosure() {
        def closure1 = { println(it) }
        closure1(22)

        def closure2 = { String str -> println(str) }
        closure2('kaka')

        def adder = { x, y -> x + y }
        println(adder(3, 5))

        def triple = { it * 3 }
        println(triple(3))
        println(triple('hello world'))

        benchmark() {
            println("xxx")
        }

        benchmark(3) {
            println("xyz")
        }

        eachLine([2, 3, 4, 8, 7]) {
            println it
        }

        eachLine([2, 3, 4, 8, 7], closure1)

        dependencies {
            classpath 'com.android.tools.build:gradle:2.1.2'
            classpath 'com.android.tools.build:gradle:xxx'
        }
    }

    def dependencies(Closure closure) {
        closure.call()
    }

    def classpath(String path) {
        println(path)
    }

    def eachLine(lines, closure) {
        for (line in lines) {
            closure(line)
        }
    }

    static def benchmark(int repeat = 1, Closure closure) {
        repeat.times {
            // closure.call()
            closure(it)

        }
    }

    void testMetaClass() {
        String.metaClass.'static'.triple = {
            return it * 3
        }
        println String.triple("abc")
    }

    void testOO() {
        Emp emp = new Emp(weight: 65f, height: 172f, sex: true)
        emp.empno = 7369
        emp.ename = 'SMITH'
        emp.job = 'CLERK'
        emp.setDeptno(20)
        emp.setMgr(7902)
        emp.setHiredate(new Date())

        println emp.display()

        // 类型别名
        EmpAlias empAlias = new EmpAlias(ename: 'King')
        println(empAlias)
    }


    /**
     * eval 的使用
     */
    void testEval() {
        // groovy.util.Eval 类是最简单的用来在运行时动态执行 Groovy 代码的类，提供了几个静态工厂方法供使用，内部其实就是对GroovyShell的封装。
        // 执行Groovy代码
        def expression = "println 'hello world'"
        Eval.me(expression)

        // 绑定自定义参数
        String result = Eval.me("age", 22, "if(age < 18) {return '未成年'} else { return '已成年'}")
        println result

        // 绑定一个名为x的参数，进行简单计算
        println Eval.x(3, "x**2") // 9
        println Eval.xy(3, 4, "x**2 + y**2") // 25
        println Eval.xyz(3, 4, 5, "x**2 + y**2 == z**2") // true

        println Eval.x("Hello world", "x.toUpperCase()") // HELLO WORLD
    }

    /**
     * GroovyShell的使用
     */
    void testGroovyShell() {
        // groovy.lang.GroovyShell除了可以执行 Groovy 代码外，提供了更丰富的功能，比如可以绑定更多的变量，从文件系统、网络加载代码等。
        // Eval.me(symbol,object, expression) 只能绑定一个参数
        GroovyShell shell = new GroovyShell()
        shell.setVariable("age", 15)
        shell.setVariable("sex", false)
        def expression = 'if (age < 18 && !sex) {return "女未成年"} else {return "已成年"}'
        println shell.evaluate(expression) // 直接求值

        // 解析为脚本，延迟执行或者缓存起来
        Script script = shell.parse(expression)
        println script.run() // 脚本跑起来

        shell.evaluate(URI.create("http://www.mocky.io/v2/5ebb9baf3600005fdef7e7df"))

//        shell.evaluate(new File("d:/hello.groovy"))
    }

    /**
     * GroovyClassLoader的使用
     */
    void testGroovyClassLoader() {
        // groovy.lang.GroovyClassLoader是一个定制的类加载器，可以在运行时加载 Groovy 代码，生成 Class 对象。
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader()

        def script = "class Hello {def hello() {println 'hello'}; def world() {println 'world'}}"
        Class clazz = groovyClassLoader.parseClass(script)
        clazz.getMethod("hello").invoke(clazz.newInstance())
        clazz.getMethod("world").invoke(clazz.newInstance())
    }

    /**
     * groovy.util.GroovyScriptEngine能够处理任何 Groovy 代码的动态编译与加载，可以从统一的位置加载脚本，并且能够监听脚本的变化，当脚本发生变化时会重新加载。
     */
    void testGroovyScriptEngine() {
        GroovyScriptEngine groovyScriptEngine = new GroovyScriptEngine("script/groovy")
        Binding binding = new Binding()
        binding.setVariable("empno", 7369)
        while (1) {
            // hello.groovy println "hello $empno"
            groovyScriptEngine.run("file:/d:/hello.groovy", binding)
            TimeUnit.SECONDS.sleep(1)
            // 这里，在运行的过程中，修改hello.groovy
        }
    }

    /**
     * JSR-223 是 Java 中调用脚本语言的标准 API。从 Java 6 开始引入进来，主要目的是用来提供一种统一的框架，
     * 以便在 Java 中调用多种脚本语言。
     * JSR-223 支持大部分流行的脚本语言，比如JavaScript、Scala、JRuby、Jython和Groovy等。
     */
    void testJSR223() {
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("JavaScript")
        Bindings bindings = new SimpleBindings()
        bindings.put("age", 22)
        Object result = scriptEngine.eval("if (age < 18) {'未成年'} else {'成年'}", bindings)
        println result
    }

    void testParseJson() {
        def jsonTxt = """
{
    "empno": 7369,
    "ename": "Lucy"
}
"""

        def emp = GsonUtil.jsonStringToObj(jsonTxt, new TypeToken<Emp>() {}.getType()) as Emp
        println emp.display()

        jsonTxt = """
[
{
    "empno": 7369,
    "ename": "Lucy"
},
{
    "empno": 9999,
    "ename": "King"
}
]
"""
//        List<Emp> empList = jsonSlurper.parseText(jsonTxt) as List<Emp>
//        empList.each {
////            println it.display()
        // 用groovy的json解析方式，会出现如下异常信息，所以还是使用GSON吧
//            //groovy.lang.MissingMethodException: No signature of method: org.apache.groovy.json.internal.LazyMap.display()
//        }

        def emps = GsonUtil.jsonStringToObj(jsonTxt, new TypeToken<List<Emp>>() {}.getType()) as List<Emp>
        println emps

        println(emps*.display())

        emps*.hiredate = new Date()

        println GsonUtil.objToJsonString(emps)

        println emps*.ename
    }
}
