package jmarijano_zadaca_3.FactoryMethod.ConcreteCreator;

import jmarijano_zadaca_3.FactoryMethod.ConcreteProduct.RoleFileProduct;
import jmarijano_zadaca_3.FactoryMethod.Creator.FileCreator;
import jmarijano_zadaca_3.FactoryMethod.Product.FileProduct;

public class RoleFileCreator implements FileCreator {

    @Override
    public FileProduct makeProduct() {
        return new RoleFileProduct();
    }
}
