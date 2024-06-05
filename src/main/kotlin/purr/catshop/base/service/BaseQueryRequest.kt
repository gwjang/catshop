package purr.catshop.base.service

abstract class BaseQueryRequest<T> {
    abstract fun toPredicate(): T
}
