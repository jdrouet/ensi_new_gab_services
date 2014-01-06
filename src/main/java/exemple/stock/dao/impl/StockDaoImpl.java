package exemple.stock.dao.impl;

import exemple.stock.dao.StockDao;
import exemple.stock.model.Stock;
import exemple.util.CustomHibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("stockDao")
public class StockDaoImpl extends CustomHibernateDaoSupport implements StockDao {

    public void save(Stock stock) {
        getHibernateTemplate().save(stock);
    }

    public void update(Stock stock) {
        getHibernateTemplate().update(stock);
    }

    public void delete(Stock stock) {
        getHibernateTemplate().delete(stock);
    }

    public Stock findByStockCode(String stockCode) {
        List list = getHibernateTemplate().find("from Stock where stockCode=?", stockCode);
        return (Stock) list.get(0);
    }

}