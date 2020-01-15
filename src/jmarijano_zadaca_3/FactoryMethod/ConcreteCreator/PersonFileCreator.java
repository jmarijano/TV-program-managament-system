package jmarijano_zadaca_3.FactoryMethod.ConcreteCreator;

import jmarijano_zadaca_3.FactoryMethod.ConcreteProduct.PersonFileProduct;
import jmarijano_zadaca_3.FactoryMethod.Creator.FileCreator;
import jmarijano_zadaca_3.FactoryMethod.Product.FileProduct;

public class PersonFileCreator implements FileCreator {

    @Override
    public FileProduct makeProduct() {
        return new PersonFileProduct();
    }
}
