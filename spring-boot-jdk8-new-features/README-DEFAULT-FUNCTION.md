# 默认方法

> 当一个接口已经有了很多实现类时，再对接口添加方法，会造成所有的方法都必须要实现该方法，否则会报错。
默认方法就是，接口可以有实现方法，而且不需要实现类去实现默认方法

* 接口
```java
interface DefaultFunctionService {
    float PI = 3.14F;

    /**
     * 在接口中使用default声明默认方法，有返回体
     */
    default float getArea(final float radius) {
        return PI * radius * radius;
    }

    void nonDefaultFunction();

}
```

* 实现

因为getArea在接口中是一个默认方法，所以它的实现类可以不用对其进行实现。

```java

class DefaultFunctionServiceImpl implements DefaultFunctionService {
    @Override
    public void nonDefaultFunction() {
        System.out.println("nothing");
    }
}
```

* 相同的方法

如果一个实现类实现了两个接口，这两个接口都分别有相同的默认方法，那这个实现类还是必须要手动实现该默认方法。

```java
interface Vehicle {
    default void print() {
        System.out.println("我是一辆车");
    }
}

interface FourWheeler {
    default void print() {
        System.out.println("我是一辆四轮车");
    }
}

class Car implements Vehicle, FourWheeler {

    @Override
    public void print() {
        Vehicle.super.print();
        FourWheeler.super.print();

        System.out.println("我是一辆车，一辆四轮车。");
    }
}
```