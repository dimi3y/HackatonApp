/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Mark Allen, Norbert Bartels.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package ru.v2bco.publishers;

import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

/**
 * Examples of RestFB's Graph API functionality.
 * 
 * @author <a href="http://restfb.com">Mark Allen</a>
 */
public class GraphApiPublisher {
  /**
   * RestFB Graph API client.
   */
  private final FacebookClient facebookClient;

  private static final Logger logger = LoggerFactory.getLogger(GraphApiPublisher.class);

  private String groupId;
  /**
   * Entry point. You must provide a single argument on the command line: a valid Graph API access token. In order for
   * publishing to succeed, you must use an access token for an application that has been granted stream_publish and
   * create_event rights.
   *
   * @param args
   *          Command-line arguments.
   * @throws IllegalArgumentException
   *           If no command-line arguments are provided.
   */
//  public static void main(String[] args) {
////    if (args.length == 0)
////      throw new IllegalArgumentException(
////        "You must provide an OAuth access token parameter. " + "See README for more information.");
//
//    new GraphApiPublisher("EAAHOMW1UjmUBADFJibvHlQLi2AD0LJM2KjoZCTxzvnYfHYvyhlpwaxjXnYCdvRMfZB0AAo59XgL2lloG9NSP76JskP9BZBu2qp4xYiqltMiBHYHhahtrWgZBdwoBP4XGmBE9AwYuNVBHP54riZBsosGS1zXfAx0r19TWNH5Toj2GbXTpnfECdCKA9BPqal4CF6Wuj6yUGxgZDZD").runEverything();
//  }

  public GraphApiPublisher(String accessToken, String groupId) {
    facebookClient = new DefaultFacebookClient(accessToken);
    this.groupId = groupId;
  }

//  void runEverything() {
////    String messageId = publishMessage();
////    delete(messageId);
////    String eventId = publishEvent();
////    delete(eventId);
//    String photoId = publishPhoto();
////    delete(photoId);
//  }

  public String publishMessage() {
    logger.info("* Feed publishing *");

    FacebookType publishMessageResponse =
        facebookClient.publish(groupId + "/feed", FacebookType.class, Parameter.with("message", "RestFB test"));

    logger.info("Published message ID: " + publishMessageResponse.getId());
    return publishMessageResponse.getId();
  }

  public String publishEvent() {
    logger.info("* Event publishing *");

    Date tomorrow = new Date(currentTimeMillis() + 1000L * 60L * 60L * 24L);
    Date twoDaysFromNow = new Date(currentTimeMillis() + 1000L * 60L * 60L * 48L);

    FacebookType publishEventResponse =
        facebookClient.publish(groupId + "/events", FacebookType.class, Parameter.with("name", "Party"),
          Parameter.with("start_time", tomorrow), Parameter.with("end_time", twoDaysFromNow));

    logger.info("Published event ID: " + publishEventResponse.getId());
    return publishEventResponse.getId();
  }

  public String publishPhoto() {
    logger.info("* Binary file publishing *");

    FacebookType publishPhotoResponse = facebookClient.publish(groupId + "/photos", FacebookType.class,
      BinaryAttachment.with("cat.png", getClass().getResourceAsStream("/cat.png")),
      Parameter.with("message", "Test cat"));

    logger.info("Published photo ID: " + publishPhotoResponse.getId());
    return publishPhotoResponse.getId();
  }

  public void delete(String objectId) {
    logger.info("* Object deletion *");
    logger.info(format("Deleted %s: %s", objectId, facebookClient.deleteObject(objectId)));
  }
}