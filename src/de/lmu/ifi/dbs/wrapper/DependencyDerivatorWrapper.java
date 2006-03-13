package de.lmu.ifi.dbs.wrapper;

import de.lmu.ifi.dbs.algorithm.DependencyDerivator;
import de.lmu.ifi.dbs.algorithm.KDDTask;
import de.lmu.ifi.dbs.utilities.optionhandling.OptionHandler;

import java.util.List;

/**
 * Wrapper class for the dependency derivator
 *
 * @author Elke Achtert (<a href="mailto:achtert@dbs.ifi.lmu.de">achtert@dbs.ifi.lmu.de</a>)
 */
public class DependencyDerivatorWrapper extends AbstractAlgorithmWrapper {

  public static void main(String[] args) {
    DependencyDerivatorWrapper derivator = new DependencyDerivatorWrapper();
    try {
      derivator.run(args);
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  /**
   * @see AbstractAlgorithmWrapper#addParameters(java.util.List<String>)
   */
  public void addParameters(List<String> parameters) {
    // algorithm DependencyDerivator
    parameters.add(OptionHandler.OPTION_PREFIX + KDDTask.ALGORITHM_P);
    parameters.add(DependencyDerivator.class.getName());

    // normalization
//    parameters.add(OptionHandler.OPTION_PREFIX + KDDTask.NORMALIZATION_P);
//    parameters.add(AttributeWiseRealVectorNormalization.class.getName());
//    parameters.add(OptionHandler.OPTION_PREFIX + KDDTask.NORMALIZATION_UNDO_F);
  }
}
