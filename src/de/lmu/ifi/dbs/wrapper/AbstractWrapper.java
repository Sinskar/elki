package de.lmu.ifi.dbs.wrapper;

import de.lmu.ifi.dbs.utilities.optionhandling.AttributeSettings;
import de.lmu.ifi.dbs.utilities.optionhandling.OptionHandler;
import de.lmu.ifi.dbs.utilities.optionhandling.Parameterizable;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * AbstractWrapper sets the values for flags verbose, time, in and out. <p/> Any
 * Wrapper class that makes use of these flags may extend this class. Beware to
 * make correct use of parameter settings via optionHandler as commented with
 * constructor and methods.
 *
 * @author Elke Achtert (<a
 *         href="mailto:achtert@dbs.ifi.lmu.de">achtert@dbs.ifi.lmu.de</a>)
 */
public abstract class AbstractWrapper implements Wrapper, Parameterizable {
  /**
   * Flag to allow verbose messages.
   */
  public static final String VERBOSE_F = "verbose";

  /**
   * Description for verbose flag.
   */
  public static final String VERBOSE_D = "flag to allow verbose messages while performing the algorithm";

  /**
   * Flag to assess runtime.
   */
  public static final String TIME_F = "time";

  /**
   * Description for time flag.
   */
  public static final String TIME_D = "flag to request output of performance time";

  /**
   * Label for parameter input.
   */
  public final static String INPUT_P = "in";

  /**
   * Description for parameter input.
   */
  public final static String INPUT_D = "<filename>input file to be parsed.";

  /**
   * Parameter output.
   */
  public static final String OUTPUT_P = "out";

  /**
   * Description for parameter output.
   */
  public static final String OUTPUT_D = "<filename>file to write the obtained results in. If an algorithm requires several outputfiles, the given filename will be used as prefix followed by automatically created markers. If this parameter is omitted, per default the output will sequentially be given to STDOUT.";

  /**
   * Map providing a mapping of parameters to their descriptions.
   */
  protected Map<String, String> parameterToDescription = new Hashtable<String, String>();

  /**
   * OptionHandler to handler options. optionHandler should be initialized
   * using parameterToDescription in any non-abstract class extending this
   * class.
   */
  protected OptionHandler optionHandler;

  /**
   * Property whether verbose messages should be allowed.
   */
  private boolean verbose;

  /**
   * Property whether runtime should be assessed.
   */
  private boolean time;

  /**
   * The input string.
   */
  private String input;

  /**
   * The output string.
   */
  private String output;

  /**
   * Sets the flags for verbose and time in the parameter map. Any extending
   * class should call this constructor, then add further parameters. Any
   * non-abstract extending class should finally initialize optionHandler like
   * this:
   * <p/>
   * <pre>
   *  {
   *      parameterToDescription.put(YOUR_PARAMETER_NAME+OptionHandler.EXPECTS_VALUE,YOUR_PARAMETER_DESCRIPTION);
   *      ...
   *      optionHandler = new OptionHandler(parameterToDescription,yourClass.class.getName());
   *  }
   * </pre>
   */
  protected AbstractWrapper() {
    parameterToDescription.put(AbstractWrapper.VERBOSE_F, AbstractWrapper.VERBOSE_D);
    parameterToDescription.put(AbstractWrapper.TIME_F, AbstractWrapper.TIME_D);
    parameterToDescription.put(AbstractWrapper.INPUT_P + OptionHandler.EXPECTS_VALUE, AbstractWrapper.INPUT_D);
    parameterToDescription.put(AbstractWrapper.OUTPUT_P + OptionHandler.EXPECTS_VALUE, AbstractWrapper.OUTPUT_D);

    optionHandler = new OptionHandler(parameterToDescription, this.getClass().getName());
  }

  /**
   * @see de.lmu.ifi.dbs.utilities.optionhandling.Parameterizable#description()
   */
  public String description() {
    return optionHandler.usage(this.getClass().getName(), false);
  }

  /**
   * @see de.lmu.ifi.dbs.utilities.optionhandling.Parameterizable#setParameters(String[])
   */
  public String[] setParameters(String[] args) {
    String[] remainingParameters = optionHandler.grabOptions(args);
    verbose = optionHandler.isSet(AbstractWrapper.VERBOSE_F);
    time = optionHandler.isSet(AbstractWrapper.TIME_F);
    input = optionHandler.getOptionValue(AbstractWrapper.INPUT_P);
    output = optionHandler.getOptionValue(AbstractWrapper.OUTPUT_P);

    return remainingParameters;
  }

  /**
   * Returns the parameter setting of the attributes.
   *
   * @return the parameter setting of the attributes
   */
  public List<AttributeSettings> getAttributeSettings() {
    return new ArrayList<AttributeSettings>();
  }

  /**
   * Returns whether the time should be assessed.
   *
   * @return whether the time should be assessed
   */
  public boolean isTime() {
    return time;
  }

  /**
   * Returns whether verbose messages should be printed while executing the
   * algorithm.
   *
   * @return whether verbose messages should be printed while executing the
   *         algorithm
   */
  public boolean isVerbose() {
    return verbose;
  }

  /**
   * Returns the input string.
   *
   * @return the input string
   */
  public String getInput() {
    return input;
  }

  /**
   * Returns the output string.
   *
   * @return the output string
   */
  public String getOutput() {
    return output;
  }
}
