package companyB.common.collections;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

/**
 * Splits Collection into a List of Lists.
 * @author C.A. Burrell (deltafront@gmail.com)
 */
@SuppressWarnings("unchecked")
public class CollectionsSplitter
{
    /**
     * Optimization strategy used in generating the list of lists.
     * @see #split(Collection, Integer, optimization_strategy)
     */
    public enum optimization_strategy
    {
        numberOfLists(CollectionsSplitter::numberOfLists),
        numberOfItemsPerList(CollectionsSplitter::numberOfItemsPerList);
        optimization_strategy(BiFunction<Collection,Integer, List<List>> function)
        {
            this.biFunction = function;
        }
        BiFunction<Collection, Integer, List<List>>biFunction;
    }

    /**
     * Returns a list of lists, wherein all contained lists have all of the items in the collection
     *
     * @param collection Collection to be split
     * @param splitNum  If you choose optimization_strategy.numberOfLists, then this will be the number of individual lists returned, with <em>1..N</em> number of items in each list
     *                   for a total of all of the items in the collection.
     *                  If you choose optimization_strategy.numberOfItemsPerList, then a List containing <em>1..N</em> number of lists will be returned, with each list
     *                   containing <strong>at most</strong> splitNum items.
     * @param strategy   one of optimization_strategy.numberOfLists | optimization_strategy.numberOfItemsPerList
     * @return list of lists.
     */
    public List<List> split(Collection collection, Integer splitNum, optimization_strategy strategy)
    {
        final Integer num = (null != collection) ?
                (splitNum == 0 || splitNum > collection.size())
                ? collection.size() : splitNum : -1;
        return  (null != collection) ?
                strategy.biFunction.apply(collection,num) :
                new LinkedList<>();
    }

    private static List<List> numberOfLists(Collection collection, int num)
    {
        final List<List> list = new LinkedList<>();
        generateAndPopulateNumberOfLists(collection, num, list);
        return list;
    }

    private static void generateAndPopulateNumberOfLists(Collection collection, int num, List<List> list)
    {
        IntStream.range(0,num).forEach(i->list.add(new LinkedList()));
        final AtomicInteger count = new AtomicInteger(0);
        collection.forEach((next)-> generateOuterLists(num, list, count, next));
    }

    private static void generateOuterLists(int num, List<List> list, AtomicInteger count, Object next)
    {
        if (count.get() == num)count.getAndSet(0);
        list.get(count.get()).add(next);
        count.getAndIncrement();
    }

    private static List<List> numberOfItemsPerList(Collection collection, int num)
    {
        final List<List> list = new LinkedList<>();
        generateAndPopulateItemsPerList(collection, num, list);
        return list;
    }

    private static void generateAndPopulateItemsPerList(Collection collection, int num, List<List> list)
    {
        final AtomicInteger count = new AtomicInteger(0);
        final List _list = new LinkedList();
        collection.forEach((next) -> generateOuterList(num, list, count, _list, next));
        if (_list.size() > 0) list.add(_list);
    }

    private static void generateOuterList(int num, List<List> list, AtomicInteger count, List _list, Object next)
    {
        _list.add(next);
        count.incrementAndGet();
        if (count.get() == num) addInnerList(list, count, _list);
    }

    private static void addInnerList(List<List> list, AtomicInteger count, List _list)
    {
        count.getAndSet(0);
        final List inner = new LinkedList<>();
        inner.addAll(_list);
        list.add(inner);
        _list.clear();
    }
}
