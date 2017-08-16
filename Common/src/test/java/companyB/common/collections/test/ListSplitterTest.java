package companyB.common.collections.test;

import companyB.common.collections.CollectionsSplitter;
import companyB.common.test.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static companyB.common.collections.CollectionsSplitter.optimization_strategy.numberOfItemsPerList;
import static companyB.common.collections.CollectionsSplitter.optimization_strategy.numberOfLists;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

@SuppressWarnings("unchecked")

@Test(groups = {"unit","list.splitter","common"})
public class ListSplitterTest extends TestBase
{
    private CollectionsSplitter collectionsSplitter;

    @BeforeMethod
    public void before()
    {
        collectionsSplitter = new CollectionsSplitter();
    }

    @DataProvider(name = "default")
    public static Object[][] data()
    {
        return new Object[][]
                {
                        {Arrays.asList("one","two"),2, numberOfLists},
                        {Arrays.asList(IntStream.range(0,8).boxed().toArray()),4, numberOfLists},
                        {Arrays.asList(IntStream.range(0,10).boxed().toArray()),10, numberOfItemsPerList},
                        {Arrays.asList(IntStream.range(0,10).boxed().toArray()),5, numberOfItemsPerList}
                };
    }

    public void nullSet()
    {
        validateEquality(0,collectionsSplitter.split(null,0, numberOfLists).size());
    }

    public void zeroSet()
    {
        validateEquality(0,collectionsSplitter.split(new LinkedList(),0, numberOfLists).size());
    }

    public void tooLargeSet()
    {
        validateEquality(0,collectionsSplitter.split(new LinkedList(),1, numberOfLists).size());
    }

    @Test(dataProvider = "default")
    public void doTest(List stream, Integer split_num, CollectionsSplitter.optimization_strategy strategy)
    {
        final Map<CollectionsSplitter.optimization_strategy,Supplier<Integer>>numberOfListsSupplier = new HashMap<>();
        numberOfListsSupplier.put(numberOfLists,()->split_num);
        numberOfListsSupplier.put(numberOfItemsPerList,()->stream.size() / split_num);

        final Map<CollectionsSplitter.optimization_strategy,Supplier<Integer>>numberOfItemsPerListSupplier = new HashMap<>();
        numberOfItemsPerListSupplier.put(numberOfLists,()->stream.size() / split_num);
        numberOfItemsPerListSupplier.put(numberOfItemsPerList,()->split_num);

        final List<List>collection = collectionsSplitter.split(stream,split_num,strategy);
        validateNotNull(collection);
        final Integer itemsPerList = numberOfItemsPerListSupplier.get(strategy).get();
        final Integer numberOfLists = numberOfListsSupplier.get(strategy).get();
        validateEquality(numberOfLists,collection.size());
        collection.forEach((list)->validateEquality(itemsPerList,list.size()));
    }
    
    public void splitNumSet()
    {
        final Set<String> set = new LinkedHashSet<>();
        IntStream.range(0,1000).forEach((index) -> set.add(String.format("Object %d",index)));
        final List<List> lists = collectionsSplitter.split(set, 42, numberOfLists);
        validateEquality(42,lists.size());
        final int size = (1000 / 42);
        lists.forEach((list)->validateAnyEquals(new Integer[]{size,size+1,size-1},list.size()));
    }

    public void itemsPerListManyLists()
    {
        final Set<String> set = new LinkedHashSet<>();
        IntStream.range(0,1002).forEach((index) -> set.add(String.format("Object %d",index)));
        final List<List> lists = collectionsSplitter.split(set, 10, CollectionsSplitter.optimization_strategy.numberOfItemsPerList);
        assertThat(lists.size(),is(equalTo(101)));
        IntStream.range(0,lists.size()).forEach((index ->{
            final List list = lists.get(index);
            final int num = (index!=lists.size()-1) ? 10 : 2;
            assertThat(list.size(),is(equalTo(num)));
        }));
    }

    public void getOptimizationValues()
    {
        for (final CollectionsSplitter.optimization_strategy strategy : CollectionsSplitter.optimization_strategy.values())
            assertThat(CollectionsSplitter.optimization_strategy.valueOf(strategy.name()),is(not(nullValue())));
    }
}