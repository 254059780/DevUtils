package dev.utils.common.able;

/**
 * detail: 通用 Operate 接口
 * @author Ttt
 * <pre>
 *     所有通用快捷 Operate 接口定义存储类
 *     全部接口只定义一个方法 execute() 且返回值一致
 *     唯一差异就是参数不同
 *     <p></p>
 *     如 Delete、Update 各种执行操作只需要知道 true、false 结果功能通用
 *     减少定义 Deleteable、Updateable 等过多相同功能接口
 *     缺点就是 如 delete()、update() 等方法都统一命名为 execute()
 *     <p></p>
 *     如果需要执行操作后, 获取返回所需值可使用 {@link Taskable}
 *     <p></p>
 *     虽然 Readable、Writeable 都可以分别使用 {@link Getable}、{@link Operateable} 实现
 *     但是读写较为常用, 所以专门定义代码与 {@link Getable}、{@link Operateable} 一致方法名、接口名不同
 * </pre>
 */
public final class Operateable {

    private Operateable() {
    }

    // =======
    // = 无参 =
    // =======

    /**
     * detail: Operate 接口 ( 最基础无参方法 )
     * @author Ttt
     */
    public interface Operate {

        boolean execute();
    }

    // =======
    // = 有参 =
    // =======

    /**
     * detail: Operate 接口 ( 通过传入参数 )
     * @author Ttt
     */
    public interface OperateByParam<Param> {

        /**
         * 通过传入参数执行操作
         * @param param 泛型参数
         * @return {@code true} success, {@code false} fail
         */
        boolean execute(Param param);
    }

    /**
     * detail: Operate 接口 ( 通过传入参数 )
     * @author Ttt
     */
    public interface OperateByParam2<Param, Param2> {

        /**
         * 通过传入参数执行操作
         * @param param  泛型参数
         * @param param2 泛型参数
         * @return {@code true} success, {@code false} fail
         */
        boolean execute(
                Param param,
                Param2 param2
        );
    }

    /**
     * detail: Operate 接口 ( 通过传入参数 )
     * @author Ttt
     */
    public interface OperateByParam3<Param, Param2, Param3> {

        /**
         * 通过传入参数执行操作
         * @param param  泛型参数
         * @param param2 泛型参数
         * @param param3 泛型参数
         * @return {@code true} success, {@code false} fail
         */
        boolean execute(
                Param param,
                Param2 param2,
                Param3 param3
        );
    }

    /**
     * detail: Operate 接口 ( 通过传入参数 )
     * @author Ttt
     */
    public interface OperateByParamArgs<Param> {

        /**
         * 通过传入参数执行操作
         * @param args 泛型参数数组
         * @return {@code true} success, {@code false} fail
         */
        boolean execute(Param... args);
    }
}