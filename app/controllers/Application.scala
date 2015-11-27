package controllers

import play.api._
import play.api.libs.json._
import play.api.mvc._
import com.windowsazure.messaging._

object Application extends Controller {

	val CONNECTION_STRING_LISTENER = "Endpoint=sb://notification-service-ns.servicebus.windows.net/;SharedAccessKeyName=DefaultListenSharedAccessSignature;SharedAccessKey=7WzEZmSExEHtJg/T1+8EWQKdZPROrWra2AUP5KADbio="
	val CONNECTION_STRING_FULL = "Endpoint=sb://notification-service-ns.servicebus.windows.net/;SharedAccessKeyName=DefaultFullSharedAccessSignature;SharedAccessKey=YUi/jCXTEEB5rUTTLXQ9L+7kjz0oWevsWdQmTZxYtJM="
	val HUB_NAME = "notification-service"

	def index = Action {
		Ok(views.html.index("Your new application is ready."))
	}

	def register = Action { implicit req =>
		println(req.body)
		Ok("success")
	}

	def sendHub = Action { implicit req => 
		req.body.asJson match {
			case Some(json) => {
				(json \ "message").asOpt[String] match {
					case Some(message) => {

						// // Create a namespace manager
						// val namespaceManager = new NamespaceManager(CONNECTION_STRING_FULL)

						// // Get hub
						// val hub = namespaceManager.getNotificationHub("notification-service")
						// // Create a hub client
						val hubClient = new NotificationHub(
							CONNECTION_STRING_FULL,
							HUB_NAME,
							"proxyserver.intercom.co.jp",
							8080)
						// val notification = Notification.createWindowsNotification(message)
						val notification = Notification.createWindowsNotification(
							"""<toast><visual><binding template="ToastText01"><text id="1">Hello from a .NET App!</text></binding></visual></toast>""")

						println("http.proxyHost: " + System.getProperty("http.proxyHost"))
						println("http.proxyPort: " + System.getProperty("http.proxyPort"))
						println("https.proxyHost: " + System.getProperty("https.proxyHost"))
						println("https.proxyPort: " + System.getProperty("https.proxyPort"))

						// broadcast
						hubClient.sendNotification(notification)

						Ok(Json.obj("message" -> "success"))
					}
					case _ => {
						BadRequest(Json.obj("message" -> "error"))
					}
				}
			}
			case _ => {
				BadRequest(Json.obj("message" -> "error"))
			}
		}
	}

}